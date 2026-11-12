/*
 * Shared front-end helpers, loaded in <head> of every authenticated page.
 * - tfCheck: validates a mutation response (HTTP status + ServiceResponse.code)
 * - tfToast: non-blocking notifications (replaces alert)
 * - duplicate-submit guard, session guard and modal keyboard/focus handling
 */
(function () {
  'use strict';

  // ---- Session guard: pages other than login require a stored session ----
  if (!/login\.html$/.test(window.location.pathname)) {
    var session = null;
    try { session = JSON.parse(localStorage.getItem('techflow_session')); } catch (e) { session = null; }
    if (!session || !session.userId || !session.token) {
      window.location.replace('login.html');
      return;
    }
  }

  // ---- Toasts ----
  function toastHost() {
    var host = document.getElementById('tf-toasts');
    if (!host) {
      host = document.createElement('div');
      host.id = 'tf-toasts';
      host.setAttribute('role', 'status');
      host.setAttribute('aria-live', 'polite');
      document.body.appendChild(host);
    }
    return host;
  }

  window.tfToast = function (message, type) {
    var el = document.createElement('div');
    el.className = 'tf-toast tf-toast--' + (type || 'error');
    el.textContent = message;
    toastHost().appendChild(el);
    setTimeout(function () { el.classList.add('tf-toast--out'); }, 3500);
    setTimeout(function () { el.remove(); }, 4000);
  };

  // ---- Mutation response check: rejects on HTTP error or ServiceResponse.code != 1 ----
  window.tfCheck = function (response) {
    return response.json().catch(function () { return {}; }).then(function (data) {
      if (!response.ok || !data || data.code !== 1) {
        throw new Error((data && data.message) || 'Não foi possível concluir a operação.');
      }
      window.tfToast('Operação realizada com sucesso.', 'success');
      return data;
    });
  };

  // ---- API calls: Bearer token, expired-session redirect, duplicate-submit guard ----
  function sessionToken() {
    try { return (JSON.parse(localStorage.getItem('techflow_session')) || {}).token; } catch (e) { return null; }
  }
  function isApi(url) {
    var u = typeof url === 'string' ? url : (url && url.url) || '';
    return u.indexOf('/api/') === 0 || u.indexOf(window.location.origin + '/api/') === 0;
  }
  var inFlight = {};
  var nativeFetch = window.fetch.bind(window);
  window.fetch = function (url, options) {
    options = Object.assign({}, options);
    if (isApi(url)) {
      var token = sessionToken();
      options.headers = Object.assign({}, options.headers, token ? { Authorization: 'Bearer ' + token } : {});
    }
    var method = (options.method || 'GET').toUpperCase();
    var key = method + ' ' + url;
    if (method !== 'GET') {
      if (inFlight[key]) return Promise.reject(new Error('Aguarde a operação em andamento.'));
      inFlight[key] = true;
      document.documentElement.classList.add('tf-busy');
    }
    return nativeFetch(url, options).then(function (response) {
      if (response.status === 401 && isApi(url) && !/login\.html$/.test(window.location.pathname)) {
        localStorage.removeItem('techflow_session');
        window.location.replace('login.html');
      }
      return response;
    }).finally(function () {
      if (method !== 'GET') {
        delete inFlight[key];
        if (!Object.keys(inFlight).length) document.documentElement.classList.remove('tf-busy');
      }
    });
  };

  // ---- Modals: Esc and backdrop click close; focus moves into the dialog ----
  function openModals() { return document.querySelectorAll('.modal.show'); }
  function closeTopModal() {
    var modals = openModals();
    if (!modals.length) return;
    var btn = modals[modals.length - 1].querySelector('[aria-label="Close"]');
    if (btn) btn.click();
  }
  document.addEventListener('keydown', function (e) {
    if (e.key === 'Escape') closeTopModal();
  });
  document.addEventListener('mousedown', function (e) {
    if (e.target.classList && e.target.classList.contains('modal') && e.target.classList.contains('show')) closeTopModal();
  });
  new MutationObserver(function (records) {
    records.forEach(function (r) {
      r.addedNodes.forEach(function (n) {
        if (n.nodeType === 1 && n.matches && n.matches('.modal.show')) {
          var field = n.querySelector('input:not([disabled]):not([type=hidden]), select:not([disabled]), textarea:not([disabled])');
          if (field) setTimeout(function () { field.focus(); }, 50);
        }
      });
    });
  }).observe(document.documentElement, { childList: true, subtree: true });
})();
