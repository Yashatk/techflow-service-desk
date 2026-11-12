// Shared header/sidebar loader and theme handling (dark HUD theme is the default).
var theme = localStorage.getItem('centaura-theme') || 'dark';
var MOBILE = window.matchMedia('(max-width: 991.98px)');

function applyTheme(t) {
  document.body.classList.remove('light', 'dark');
  document.body.classList.add(t);
  document.documentElement.setAttribute('data-bs-theme', t);
  var sidebar = document.getElementById('sidebar-menu');
  if (sidebar) sidebar.classList[t === 'dark' ? 'add' : 'remove']('dark');
  var header = document.getElementById('main-header');
  if (header) {
    header.classList.remove('navbar-dark', 'navbar-light');
    header.classList.add(t === 'dark' ? 'navbar-dark' : 'navbar-light');
  }
  var icon = document.getElementById('theme-toggle-icon');
  if (icon) icon.innerHTML = t === 'light' ? '🌙 <span class="tf-theme-label">Escuro</span>' : '☀️ <span class="tf-theme-label">Claro</span>';
}
applyTheme(theme);

function closeSidebar() {
  document.body.classList.remove('sidebar-open');
}

// Sidebar include
fetch('sidebar.html').then(r => r.text()).then(html => {
  document.getElementById('sidebar-container').innerHTML = html;
  applyTheme(theme);
  if (window.location.pathname.endsWith(SYS_PAGE_NAME)) {
    document.getElementById(SYS_MENU_ID).classList.add('active');
  }
}).catch(function() { tfToast('Falha ao carregar o menu.'); });

// Header include
fetch('header.html').then(r => r.text()).then(html => {
  document.getElementById('header-container').innerHTML = html;
  applyTheme(theme);

  document.getElementById('theme-toggle').onclick = function() {
    theme = theme === 'light' ? 'dark' : 'light';
    localStorage.setItem('centaura-theme', theme);
    applyTheme(theme);
    if (window.app && window.app.theme !== undefined) window.app.theme = theme;
  };

  // Mobile: sidebar slides over the content; desktop: collapses it
  document.getElementById('menu-toggle').onclick = function() {
    if (MOBILE.matches) {
      document.body.classList.toggle('sidebar-open');
    } else {
      document.body.classList.toggle('sidebar-hidden');
    }
  };
  document.getElementById('tf-backdrop').onclick = closeSidebar;
  document.addEventListener('keydown', function(e) { if (e.key === 'Escape') closeSidebar(); });
  MOBILE.addEventListener('change', closeSidebar);

  // Username and logout
  var session = JSON.parse(localStorage.getItem('techflow_session')) || {};
  document.getElementById('header-username').textContent = session.name || '';
  document.getElementById('logout').onclick = function() {
    if (confirm('Deseja realmente sair do sistema?')) {
      localStorage.removeItem('techflow_session');
      window.location.href = 'login.html';
    }
  };
}).catch(function() { tfToast('Falha ao carregar o cabeçalho.'); });
