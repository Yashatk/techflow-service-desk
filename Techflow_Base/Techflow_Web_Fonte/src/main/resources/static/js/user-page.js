/**
 * Sort Fix - Table sorting utility
 */
(function() {
  // Wait for DOM to be loaded
  document.addEventListener('DOMContentLoaded', function() {
    // Override Vue's sortBy method with a more reliable version
    const checkInterval = setInterval(function() {
      if (window.userPageVue) {
        // Replace with our fixed version
        window.userPageVue.sortBy = function(key) {
          // Determine the new sort state
          let newOrder;
          
          if (this.sortKey === key) {
            // Same column, toggle sort direction
            const currentOrder = this.sortOrder === -1 ? -1 : 1;
            newOrder = currentOrder * -1; // Multiply by -1 to invert
          } else {
            // New column, default to ascending
            newOrder = 1;
          }
          
          // Apply the new sort state
          this.sortKey = key;
          this.sortOrder = newOrder;
          
          // Save to localStorage
          localStorage.setItem('user-table-sort-key', key);
          localStorage.setItem('user-table-sort-order', newOrder === 1 ? '1' : '-1');
          
          // Update UI indicators
          this.$nextTick(() => {
            // Remove existing indicators
            document.querySelectorAll('.sortable').forEach(col => {
              col.classList.remove('active-sort', 'asc', 'desc');
            });
            
            // Add new indicator
            const activeCol = document.getElementById(`sort-${key}`);
            if (activeCol) {
              activeCol.classList.add('active-sort');
              activeCol.classList.add(newOrder === 1 ? 'asc' : 'desc');
            }
          });
          
          // Force UI update
          this.$forceUpdate();
        };
        
        clearInterval(checkInterval);
      }
    }, 100);
  });
  
  // Function to directly sort from outside Vue
  window.directSort = function(key) {
    // Prevent event propagation
    event && event.stopPropagation();
    
    // Get current state
    const currentKey = localStorage.getItem('user-table-sort-key') || 'id';
    const currentOrderStr = localStorage.getItem('user-table-sort-order') || '1';
    
    // Determine new sort state
    let newOrder;
    if (currentKey === key) {
      // Same column - toggle
      newOrder = currentOrderStr === '1' ? '-1' : '1';
    } else {
      // New column - ascending
      newOrder = '1';
    }
    
    localStorage.setItem('user-table-sort-key', key);
    localStorage.setItem('user-table-sort-order', newOrder);
    
    // Update Vue instance if available
    if (window.userPageVue) {
      window.userPageVue.sortKey = key;
      window.userPageVue.sortOrder = newOrder === '1' ? 1 : -1;
      
      // Apply visual changes
      window.userPageVue.$nextTick(() => {
        document.querySelectorAll('.sortable').forEach(col => {
          col.classList.remove('active-sort', 'asc', 'desc');
        });
        
        const activeCol = document.getElementById(`sort-${key}`);
        if (activeCol) {
          activeCol.classList.add('active-sort');
          activeCol.classList.add(newOrder === '1' ? 'asc' : 'desc');
        }
        
        // Force update
        window.userPageVue.$forceUpdate();
      });
    }
  };
})();

/**
 * User Page Script
 * Handles user-specific functionality and data
 */

/**
 * Initializes modal functionality after DOM is loaded or SPA navigation
 * This ensures Bootstrap modals work correctly even after SPA navigation
 */
function initializeModals() {
  // Clean up any existing modal backdrop elements to prevent issues
  const existingBackdrops = document.querySelectorAll('.modal-backdrop');
  existingBackdrops.forEach(el => el.remove());
  
  // Reset body classes that might be left from previous modals
  document.body.classList.remove('modal-open');
  document.body.style.paddingRight = '';
  document.body.style.overflow = '';
  
  // Make sure modals are hidden by default (fixes visual issues after SPA navigation)
  const modals = document.querySelectorAll('.modal');
  modals.forEach(modal => {
    modal.style.display = 'none';
    modal.classList.remove('show');
    modal.setAttribute('aria-hidden', 'true');
    modal.removeAttribute('aria-modal');
    modal.removeAttribute('role');
    
    // Ensure the modal can be initialized with Bootstrap
    try {
      // Dispose of any existing modal instance first
      const bsInstance = bootstrap.Modal.getInstance(modal);
      if (bsInstance) {
        bsInstance.dispose();
      }
    } catch (e) {
      // Ignore errors during disposal
    }
  });
  
  // Re-initialize the modal objects
  try {
    // User modal
    const userModalEl = document.getElementById('userModal');
    if (userModalEl && typeof bootstrap !== 'undefined') {
      // Store in global space for direct access
      window.userModalInstance = new bootstrap.Modal(userModalEl, {
        backdrop: 'static',
        keyboard: false,
        focus: true
      });
      
      // Add direct click handler to the add user button
      const addUserBtn = document.getElementById('addUserBtn');
      if (addUserBtn) {
        addUserBtn.addEventListener('click', function() {
          if (window.userPageVue && typeof window.userPageVue.openAddUserModal === 'function') {
            window.userPageVue.openAddUserModal();
          }
        });
      }
    }
    
    // Delete modal
    const deleteModalEl = document.getElementById('deleteModal');
    if (deleteModalEl && typeof bootstrap !== 'undefined') {
      // Store in global space for direct access
      window.deleteModalInstance = new bootstrap.Modal(deleteModalEl, {
        backdrop: 'static',
        keyboard: false,
        focus: true
      });
    }
    
  } catch (err) {
    console.error('Error initializing modals:', err);
  }
}

/**
 * Ensures the current theme is properly applied to all UI elements (tables, cards, etc.)
 */
function applyPageTheme() {
  const currentTheme = localStorage.getItem('centaura-theme') || 'light';
  
  // Make sure body class is set correctly
  document.body.className = currentTheme;
  
  // Force repaint of table elements to ensure CSS is applied
  const userTable = document.querySelector('.user-table');
  if (userTable) {
    // Trigger a reflow/repaint by accessing offsetHeight
    void userTable.offsetHeight;
    
    // Make sure table has correct theme classes
    if (currentTheme === 'dark') {
      userTable.classList.add('table-dark');
      
      // Ensure table header has correct background
      const tableHeader = document.querySelector('.user-table-header');
      if (tableHeader) {
        tableHeader.style.backgroundColor = '#343a40';
      }
    } else {
      userTable.classList.remove('table-dark');
    }
  }
  
  // Apply theme to cards
  const cards = document.querySelectorAll('.card');
  cards.forEach(card => {
    if (currentTheme === 'dark') {
      card.style.backgroundColor = '#23272b';
      card.style.borderColor = '#495057';
      
      // Apply style to card headers
      const cardHeader = card.querySelector('.card-header');
      if (cardHeader) {
        cardHeader.style.backgroundColor = '#343a40';
        cardHeader.style.borderColor = '#495057';
        cardHeader.style.color = '#e0e0e0';
        
        // Ensure all text elements in card header have proper color
        const textElements = cardHeader.querySelectorAll('span, h1, h2, h3, h4, h5, h6, p, div');
        textElements.forEach(el => {
          el.style.color = '#e0e0e0';
        });
      }
      
      // Apply style to card bodies
      const cardBody = card.querySelector('.card-body');
      if (cardBody) {
        cardBody.style.color = '#e0e0e0';
      }
    } else {
      // Reset to default light theme styles
      card.style.backgroundColor = '';
      card.style.borderColor = '';
      
      const cardHeader = card.querySelector('.card-header');
      if (cardHeader) {
        cardHeader.style.backgroundColor = '';
        cardHeader.style.borderColor = '';
        cardHeader.style.color = '';
        
        const textElements = cardHeader.querySelectorAll('span, h1, h2, h3, h4, h5, h6, p, div');
        textElements.forEach(el => {
          el.style.color = '';
        });
      }
      
      const cardBody = card.querySelector('.card-body');
      if (cardBody) {
        cardBody.style.color = '';
      }
    }
  });
}

function initializeUserPage() {
  // Check if bootstrap is available
  if (typeof bootstrap === 'undefined') {
    console.error('Bootstrap is not defined. Attempting to load bootstrap.bundle.min.js...');
    // Try to load Bootstrap if it's not already available
    const script = document.createElement('script');
    script.src = '/js/bootstrap.bundle.min.js';
    script.onload = () => {
      setTimeout(initializeUserPage, 100);
    };
    document.head.appendChild(script);
    return null; // Exit initialization until bootstrap is loaded
  }
  
  // Clean up any existing Vue instance and modals
  if (window.userPageVue) {
    // Dispose of any modal instances first
    if (window.userPageVue.userModal) {
      try {
        window.userPageVue.userModal.dispose();
      } catch (e) {
        // Ignore disposal errors
      }
    }
    if (window.userPageVue.deleteModal) {
      try {
        window.userPageVue.deleteModal.dispose();
      } catch (e) {
        // Ignore disposal errors
      }
    }
    
    // Then destroy the Vue instance
    window.userPageVue.$destroy();
  }
  
  // Clean up any modal backdrop elements
  document.querySelectorAll('.modal-backdrop').forEach(el => el.remove());
  
  // Reset body classes that might be left from previous modals
  document.body.classList.remove('modal-open');
  document.body.style.paddingRight = '';
  document.body.style.overflow = '';
  
  // Create new Vue instance for this page
  window.userPageVue = new Vue({
    el: '#app',
    data: {
      // Common data
      theme: localStorage.getItem('centaura-theme') || 'light',
      
      // User data
      users: [],
      loading: true,
      error: null,
      
      // Sorting - persist sort preferences across reloads
      // Use window.initialSortKey if available (set by early initialization)
      sortKey: window.initialSortKey || localStorage.getItem('user-table-sort-key') || 'id',
      // Careful handling of sort order - ensuring it's exactly 1 or -1
      sortOrder: (function() {
        // First try window.initialSortOrder if available
        if (window.initialSortOrder === -1) return -1;
        if (window.initialSortOrder === 1) return 1;
        
        // Next try localStorage
        const storedOrder = localStorage.getItem('user-table-sort-order');
        if (storedOrder === "-1") return -1;
        
        // Default to ascending (1) for any other case
        return 1;
      })(), // Immediately invoked function
      
      // Pagination
      pageSize: 10,
      currentPage: 1,
      
      // New/Edit User Modal
      showUserModal: false,
      currentUser: {
        name: '',
        email: '',
        role: 'USER',
        active: true
      },
      isEditMode: false,
      
      // Delete Confirmation
      showDeleteModal: false,
      userToDelete: {
        name: '',
        email: '',
        role: '',
        active: true
      }
    },
    computed: {
      mainContentStyle() {
        return {
          paddingTop: '50px',
          marginLeft: document.body.classList.contains('sidebar-hidden') ? '0' : '220px',
          transition: 'margin-left 0.2s',
          maxWidth: '95%',
          width: document.body.classList.contains('sidebar-hidden') ? '100%' : 'calc(100% - 240px)',
          marginRight: 'auto',
        };
      },
      sortedUsers() {
        // Make sure we have valid sort parameters
        const key = this.sortKey || localStorage.getItem('user-table-sort-key') || 'id';
        
        // Careful handling of sort order to ensure it's exactly 1 or -1
        let order;
        // First try from this.sortOrder (Vue data property)
        if (this.sortOrder === -1) {
          order = -1;
        } else if (this.sortOrder === 1) {
          order = 1;
        } else {
          // Log warning if sortOrder is not exactly 1 or -1
          console.warn(`Invalid sortOrder detected in sortedUsers: ${this.sortOrder} (type: ${typeof this.sortOrder})`);
          
          // Fallback to localStorage if Vue data is invalid
          const orderStr = localStorage.getItem('user-table-sort-order');
          order = orderStr === "-1" ? -1 : 1;
          
          // Fix the sortOrder value in Vue data
          this.sortOrder = order;
        }
        
        if (!this.users || !this.users.length) {
          return [];
        }
        
        // Make a defensive copy of the users array to avoid mutating the original
        return [...this.users].sort((a, b) => {
          // Handle null or undefined values safely
          const valA = a && key in a ? a[key] : '';
          const valB = b && key in b ? b[key] : '';
          
          let result = 0;
          
          // Special handling for boolean values (like 'active' property)
          if (typeof valA === 'boolean' && typeof valB === 'boolean') {
            // For booleans, true comes before false in ascending order
            if (valA === valB) {
              result = 0;
            } else {
              result = valA ? -1 : 1;
            }
          }
          // Sort strings using localeCompare for proper alphabetical sorting
          else if (typeof valA === 'string' && typeof valB === 'string') {
            result = valA.localeCompare(valB, undefined, {sensitivity: 'base'});
          }
          // Safe numerical comparison
          else {
            if (valA === valB) {
              result = 0;
            } else if (valA === null || valA === undefined) {
              result = -1;
            } else if (valB === null || valB === undefined) {
              result = 1;
            } else {
              result = valA > valB ? 1 : -1;
            }
          }
          
          // Apply sort order - ensure it's exactly 1 or -1
          return (order === -1 ? -1 : 1) * result;
        });
      }
    },
    methods: {
      fetchUsers() {
        this.loading = true;
        this.error = null;
        
        // Save current sort state before fetch
        const savedSortKey = this.sortKey;
        const savedSortOrder = this.sortOrder;
        
        // Add a timestamp to prevent caching issues
        const timestamp = new Date().getTime();
        
        fetch(`/api/user?uid=1&sid=sIKgRpMsJsccAydaMT066bORYwwXeM8z&_=${timestamp}`)
          .then(response => {
            if (!response.ok) {
              throw new Error(`Erro ${response.status}: ${response.statusText}`);
            }
            return response.json();
          })
          .then(data => {
            // Handle both direct array response or wrapped object
            this.users = Array.isArray(data) ? data : (data.obj || []);
            this.loading = false;
            
            // Restore sort state from before the fetch if it was lost
            if (savedSortKey && !this.sortKey) {
              this.sortKey = savedSortKey;
            }
            if (savedSortOrder && !this.sortOrder) {
              this.sortOrder = savedSortOrder;
            }
            
            // Apply saved sort preferences from localStorage
            this.applySorting();
            
            // Update table headers with proper sort indicators
            this.$nextTick(() => {
              const sortColumns = document.querySelectorAll('.sortable');
              sortColumns.forEach(col => {
                col.classList.remove('active-sort', 'asc', 'desc');
                const columnKey = col.getAttribute('id').replace('sort-', '');
                if (columnKey === this.sortKey) {
                  col.classList.add('active-sort');
                  col.classList.add(this.sortOrder === 1 ? 'asc' : 'desc');
                }
              });
              
              // Force update to ensure UI reflects the sorting
              this.$forceUpdate();
              
              // Dispatch an event that other scripts can listen for
              // This is crucial for SPA navigation sorting to work
              document.dispatchEvent(new CustomEvent('userDataLoaded', {
                detail: { 
                  sortKey: this.sortKey,
                  sortOrder: this.sortOrder
                }
              }));
              
              // Use the global sort function if available for redundancy
              if (typeof window.applyUserTableSort === 'function') {
                setTimeout(() => window.applyUserTableSort(true), 100);
              }
            });
          })
          .catch(err => {
            this.error = err.message;
            this.loading = false;
            console.error('Error fetching users:', err);
          });
      },
      
      // Method to ensure sorting is applied correctly
      applySorting() {
        
        // Load saved sort preferences from localStorage 
        const storedKey = localStorage.getItem('user-table-sort-key');
        const storedOrderStr = localStorage.getItem('user-table-sort-order');
        
        // Ensure we have valid sort parameters
        if (!this.sortKey && storedKey) {
          this.sortKey = storedKey;
        } else if (!this.sortKey) {
          this.sortKey = 'id'; // Default sort column
        }
        
        // Parse sort order with careful handling
        if (this.sortOrder === undefined && storedOrderStr) {
          // Handle string values explicitly
          if (storedOrderStr === "-1") {
            this.sortOrder = -1;
          } else {
            // Default to 1 for any other value
            this.sortOrder = 1;
          }
        } else if (this.sortOrder === undefined) {
          this.sortOrder = 1; // Default ascending order
        }
        
        // Force normalization to exactly 1 or -1
        if (this.sortOrder !== 1 && this.sortOrder !== -1) {
          this.sortOrder = (this.sortOrder < 0) ? -1 : 1;
        }
        
        // Save current sort preferences to localStorage with explicit string values
        localStorage.setItem('user-table-sort-key', this.sortKey);
        localStorage.setItem('user-table-sort-order', this.sortOrder === -1 ? "-1" : "1");
        
        // Highlight the active sort column
        this.$nextTick(() => {
          const sortColumns = document.querySelectorAll('.sortable');
          sortColumns.forEach(col => {
            col.classList.remove('active-sort', 'asc', 'desc');
          });
          
          const activeCol = document.getElementById(`sort-${this.sortKey}`);
          if (activeCol) {
            activeCol.classList.add('active-sort');
            activeCol.classList.add(this.sortOrder === 1 ? 'asc' : 'desc');
            
            // Force reflow/repaint
            void activeCol.offsetHeight;
          }
          
          // Add direct click handlers for sort columns as a backup in case Vue event binding is lost
          Object.entries({
            'sort-id': 'id',
            'sort-name': 'name', 
            'sort-email': 'email',
            'sort-role': 'role',
            'sort-active': 'active'
          }).forEach(([elementId, columnKey]) => {
            const sortColumn = document.getElementById(elementId);
            if (sortColumn) {
              // Remove old handlers by cloning and replacing
              const newColumn = sortColumn.cloneNode(true);
              if (sortColumn.parentNode) {
                sortColumn.parentNode.replaceChild(newColumn, sortColumn);
              }
              
              // Add new handler
              newColumn.addEventListener('click', () => {
                if (this && typeof this.sortBy === 'function') {
                  this.sortBy(columnKey);
                }
              });
            }
          });
        });
      },
      sortBy(key) {
        // Ensure sortOrder is normalized
        const normalizedOrder = this.sortOrder === -1 ? -1 : 1;
        
        // If clicking the same column, toggle sort order
        if (this.sortKey === key) {
          this.sortOrder = normalizedOrder === 1 ? -1 : 1;
        } else {
          // New column - set to ascending order
          this.sortKey = key;
          this.sortOrder = 1;
        }
        
        // Update visual indicators immediately
        this.$nextTick(() => {
          // Remove all current sort indicators
          document.querySelectorAll('.sortable').forEach(col => {
            col.classList.remove('active-sort', 'asc', 'desc');
          });
          
          // Add indicator to the current sort column
          const activeCol = document.getElementById(`sort-${this.sortKey}`);
          if (activeCol) {
            activeCol.classList.add('active-sort');
            activeCol.classList.add(this.sortOrder === 1 ? 'asc' : 'desc');
          }
        });
        
        // Store sorting preference in localStorage
        localStorage.setItem('user-table-sort-key', this.sortKey);
        localStorage.setItem('user-table-sort-order', this.sortOrder === 1 ? "1" : "-1");
        
        // Force a reactivity update to ensure the table re-renders
        this.$forceUpdate();
      },
      getSortIcon(key) {
        if (this.sortKey !== key) return '';
        
        // Ensure sortOrder is exactly 1 or -1
        const normalizedOrder = this.sortOrder === -1 ? -1 : 1;
        
        return normalizedOrder === 1 
          ? 'bi bi-caret-up-fill' 
          : 'bi bi-caret-down-fill';
      },
      getColumnClass(key) {
        // Ensure sortOrder is normalized to exactly 1 or -1
        const normalizedOrder = this.sortOrder === -1 ? -1 : 1;
        
        return {
          'active-sort': this.sortKey === key,
          'asc': this.sortKey === key && normalizedOrder === 1,
          'desc': this.sortKey === key && normalizedOrder === -1
        };
      },
      // Method to reset sorting to defaults
      resetSorting() {
        // Reset to default values
        this.sortKey = 'id';
        this.sortOrder = 1;
        
        // Update localStorage
        localStorage.setItem('user-table-sort-key', 'id');
        localStorage.setItem('user-table-sort-order', '1');
        
        // Apply visual updates
        this.$nextTick(() => {
          // Remove all current sort indicators
          document.querySelectorAll('.sortable').forEach(col => {
            col.classList.remove('active-sort', 'asc', 'desc');
          });
          
          // Add indicator to the id column
          const activeCol = document.getElementById('sort-id');
          if (activeCol) {
            activeCol.classList.add('active-sort', 'asc');
          }
          
          // Force update to ensure the view reflects the current sort state
          this.$forceUpdate();
        });
      },
      openAddUserModal() {
        this.isEditMode = false;
        this.currentUser = {
          name: '',
          email: '',
          role: 'USER',
          active: true
        };
        
        const showModal = () => {
          // Use global instance first if available (prevents issues with SPA navigation)
          if (window.userModalInstance) {
            try {
              this.userModal = window.userModalInstance;
              window.userModalInstance.show();
              return;
            } catch (err) {
              console.warn('Error using global modal instance, falling back to local instance', err);
            }
          }
          
          // Fallback to local instance if global instance is not available or fails
          const modalElement = document.getElementById('userModal');
          if (modalElement) {
            try {
              // Clean up any existing instance
              if (this.userModal) {
                try {
                  this.userModal.dispose();
                } catch (e) {
                  // Ignore disposal errors
                }
              }
              
              // Always create a fresh modal instance to avoid SPA navigation issues
              const bsModal = new bootstrap.Modal(modalElement, {
                backdrop: 'static',
                keyboard: false,
                focus: true
              });
              
              // Store for later reference
              this.userModal = bsModal;
              window.userModalInstance = bsModal;  // Also store globally
              
              // Reset modal state before showing
              modalElement.style.display = 'block';
              modalElement.removeAttribute('aria-hidden');
              modalElement.setAttribute('aria-modal', 'true');
              modalElement.setAttribute('role', 'dialog');
              
              // Show the modal
              bsModal.show();
            } catch (err) {
              console.error('Error showing user modal:', err);
              
              // Fallback method if bootstrap modal API fails
              try {
                modalElement.classList.add('show');
                modalElement.style.display = 'block';
                document.body.classList.add('modal-open');
                
                // Create manual backdrop
                const backdrop = document.createElement('div');
                backdrop.className = 'modal-backdrop fade show';
                document.body.appendChild(backdrop);
              } catch (e) {
                console.error('Even manual modal display failed', e);
              }
            }
          } else {
            console.error('User modal element not found in the DOM');
          }
        };
        
        // Use Bootstrap 5 Modal API if available, otherwise try to load it
        if (typeof bootstrap === 'undefined') {
          console.error('Bootstrap is not defined. Attempting to load bootstrap.bundle.min.js...');
          const script = document.createElement('script');
          script.src = '/js/bootstrap.bundle.min.js';
          script.onload = () => {
            showModal();
          };
          document.head.appendChild(script);
        } else {
          showModal();
        }
      },
      hideUserModal() {
        try {
          // Try using the bootstrap API first
          if (this.userModal) {
            this.userModal.hide();
          } else {
            // Fallback if modal instance is not available
            const modalElement = document.getElementById('userModal');
            if (modalElement) {
              // Try to create a new instance and hide it
              const modal = new bootstrap.Modal(modalElement);
              modal.hide();
            }
          }
        } catch (err) {
          console.error('Error hiding modal:', err);
          // Manual fallback if bootstrap API fails
          const modalElement = document.getElementById('userModal');
          if (modalElement) {
            modalElement.classList.remove('show');
            modalElement.style.display = 'none';
            modalElement.setAttribute('aria-hidden', 'true');
            modalElement.removeAttribute('aria-modal');
            modalElement.removeAttribute('role');
            
            // Remove backdrop manually
            const backdrop = document.querySelector('.modal-backdrop');
            if (backdrop) backdrop.remove();
            
            // Reset body
            document.body.classList.remove('modal-open');
            document.body.style.overflow = '';
            document.body.style.paddingRight = '';
          }
        }
      },
      saveUser() {
        // Implement save logic here
        this.hideUserModal();
      },
      editUser(user) {
        // Ensure we have a valid user object before proceeding
        if (!user || typeof user !== 'object') {
          console.error('Invalid user object provided to editUser:', user);
          return;
        }
        
        this.isEditMode = true;
        
        // Create a clean copy with default values for any missing properties
        this.currentUser = {
          id: user.id || 0,
          name: user.name || '',
          email: user.email || '',
          role: user.role || 'USER',
          active: user.active !== undefined ? user.active : true
        };
        
        // Use global instance first if available (prevents issues with SPA navigation)
        if (window.userModalInstance) {
          try {
            this.userModal = window.userModalInstance;
            window.userModalInstance.show();
            return;
          } catch (err) {
            console.warn('Error using global modal instance for edit, falling back to local instance', err);
          }
        }
        
        // Fallback to local instance if global instance is not available or fails
        const modalElement = document.getElementById('userModal');
        if (modalElement) {
          try {
            // Clean up any existing instance
            if (this.userModal) {
              try {
                this.userModal.dispose();
              } catch (e) {
                // Ignore disposal errors
              }
            }
            
            // Always create a fresh modal instance to avoid SPA navigation issues
            const bsModal = new bootstrap.Modal(modalElement, {
              backdrop: 'static',
              keyboard: false,
              focus: true
            });
            
            // Store for later reference
            this.userModal = bsModal;
            window.userModalInstance = bsModal;  // Also store globally
            
            // Reset modal state before showing
            modalElement.style.display = 'block';
            modalElement.removeAttribute('aria-hidden');
            modalElement.setAttribute('aria-modal', 'true');
            modalElement.setAttribute('role', 'dialog');
            
            // Show the modal
            bsModal.show();
          } catch (err) {
            console.error('Error showing edit user modal:', err);
            
            // Fallback method if bootstrap modal API fails
            try {
              modalElement.classList.add('show');
              modalElement.style.display = 'block';
              document.body.classList.add('modal-open');
              
              // Create manual backdrop
              const backdrop = document.createElement('div');
              backdrop.className = 'modal-backdrop fade show';
              document.body.appendChild(backdrop);
            } catch (e) {
              console.error('Even manual modal display failed', e);
            }
          }
        } else {
          console.error('User modal element not found in the DOM');
        }
      },
      confirmDeleteUser(user) {
        // Ensure we have a valid user object before proceeding
        if (!user || typeof user !== 'object') {
          console.error('Invalid user object provided to confirmDeleteUser:', user);
          return;
        }
        
        // Make a clean copy to avoid reference issues
        this.userToDelete = {
          id: user.id || 0,
          name: user.name || '(Nome não disponível)',
          email: user.email || '',
          role: user.role || '',
          active: user.active !== undefined ? user.active : true
        };
        
        // Use global instance first if available (prevents issues with SPA navigation)
        if (window.deleteModalInstance) {
          try {
            this.deleteModal = window.deleteModalInstance;
            window.deleteModalInstance.show();
            return;
          } catch (err) {
            console.warn('Error using global delete modal instance, falling back to local instance', err);
          }
        }
        
        // Use Bootstrap 5 Modal API
        const modalElement = document.getElementById('deleteModal');
        if (modalElement) {
          try {
            // Clean up any existing instance
            if (this.deleteModal) {
              try {
                this.deleteModal.dispose();
              } catch (e) {
                // Ignore disposal errors
              }
            }
            
            // Always create a fresh modal instance to avoid SPA navigation issues
            const bsModal = new bootstrap.Modal(modalElement, {
              backdrop: 'static',
              keyboard: false,
              focus: true
            });
            
            // Store for later reference
            this.deleteModal = bsModal;
            window.deleteModalInstance = bsModal;  // Also store globally
            
            // Reset modal state before showing
            modalElement.style.display = 'block';
            modalElement.removeAttribute('aria-hidden');
            modalElement.setAttribute('aria-modal', 'true');
            modalElement.setAttribute('role', 'dialog');
            
            // Show the modal
            bsModal.show();
          } catch (err) {
            console.error('Error showing delete confirmation modal:', err);
            
            // Fallback method if bootstrap modal API fails
            try {
              modalElement.classList.add('show');
              modalElement.style.display = 'block';
              document.body.classList.add('modal-open');
              
              // Create manual backdrop
              const backdrop = document.createElement('div');
              backdrop.className = 'modal-backdrop fade show';
              document.body.appendChild(backdrop);
            } catch (e) {
              console.error('Even manual modal display failed', e);
            }
          }
        } else {
          console.error('Delete modal element not found in the DOM');
        }
      },
      hideDeleteModal() {
        try {
          // Try using the bootstrap API first
          if (this.deleteModal) {
            this.deleteModal.hide();
          } else {
            // Fallback if modal instance is not available
            const modalElement = document.getElementById('deleteModal');
            if (modalElement) {
              // Try to create a new instance and hide it
              const modal = new bootstrap.Modal(modalElement);
              modal.hide();
            }
          }
        } catch (err) {
          console.error('Error hiding delete modal:', err);
          // Manual fallback if bootstrap API fails
          const modalElement = document.getElementById('deleteModal');
          if (modalElement) {
            modalElement.classList.remove('show');
            modalElement.style.display = 'none';
            modalElement.setAttribute('aria-hidden', 'true');
            modalElement.removeAttribute('aria-modal');
            modalElement.removeAttribute('role');
            
            // Remove backdrop manually
            const backdrop = document.querySelector('.modal-backdrop');
            if (backdrop) backdrop.remove();
            
            // Reset body
            document.body.classList.remove('modal-open');
            document.body.style.overflow = '';
            document.body.style.paddingRight = '';
          }
        }
      },
      deleteUser() {
        // Check if we have a valid user to delete
        if (this.userToDelete && this.userToDelete.id) {
          console.log('Deleting user:', this.userToDelete);
          // Here you would add the actual API call to delete the user
          // For example:
          // fetch(`/api/user/${this.userToDelete.id}`, { method: 'DELETE' })
          //   .then(response => {
          //     if (response.ok) {
          //       this.fetchUsers(); // Refresh the list
          //     }
          //   });
        } else {
          console.error('Cannot delete user: Invalid user data', this.userToDelete);
        }
        
        this.hideDeleteModal();
      },
      getRoleName(role) {
        const roles = {
          'ADMIN': 'Administrador',
          'USER': 'Usuário',
          'GUEST': 'Convidado'
        };
        return roles[role] || role;
      },
      getRoleBadgeClass(role) {
        const classes = {
          'ADMIN': 'bg-primary',
          'USER': 'bg-success',
          'GUEST': 'bg-secondary'
        };
        return classes[role] || 'bg-info';
      }
    },
    watch: {
      theme(newTheme) {
        document.body.className = newTheme;
        localStorage.setItem('centaura-theme', newTheme);
        
        // Apply theme changes to table when theme changes
        setTimeout(applyPageTheme, 50);
      },
      // Watch for changes in the users array to apply theme after data loads
      users: {
        handler() {
          // Apply theme after data loads and DOM updates
          setTimeout(applyPageTheme, 100);
        }
      }
    },
    mounted() {
      // Apply theme correctly
      document.body.className = this.theme;
      
      // Load sort settings from localStorage
      const storedKey = localStorage.getItem('user-table-sort-key');
      const storedOrder = localStorage.getItem('user-table-sort-order');
      
      if (storedKey) {
        this.sortKey = storedKey;
        this.sortOrder = storedOrder ? parseInt(storedOrder, 10) : 1;
      }
      
      // Load header and sidebar
      loadHeader(this);
      loadSidebar('menu-registerdata');
      
      // Fetch users data
      this.fetchUsers();
      
      // Apply theme to UI elements after data is loaded
      setTimeout(() => {
        applyPageTheme();
      }, 200);
      
      // Initialize modals
      setTimeout(initializeModals, 250);
      
      // Apply sorting
      setTimeout(() => {
        this.applySorting();
      }, 500);
      
      // Set up theme toggle listener
      window.addEventListener('themeChanged', (e) => {
        if (e.detail && e.detail.theme) {
          this.theme = e.detail.theme;
          // Apply theme changes immediately when theme changes
          setTimeout(applyPageTheme, 50);
        }
      });
      
      // Set active menu item
      setTimeout(() => {
        const userLink = document.querySelector('#menu-registerdata');
        if (userLink) {
          const links = document.querySelectorAll('#sidebar-menu .nav-link');
          links.forEach(link => link.classList.remove('active'));
          userLink.classList.add('active');
        }
      }, 100);
    }
  });
  
  return window.userPageVue;
}

// Export for direct access
window.initializeUserPage = initializeUserPage;

// Initialize on page load
document.addEventListener('DOMContentLoaded', function() {
  // Check if we're on the user page
  const currentPath = window.location.pathname;
  if (currentPath.endsWith('user.html')) {
    // Apply theme immediately to prevent flash of unstyled content
    const currentTheme = localStorage.getItem('centaura-theme') || 'light';
    document.body.className = currentTheme;
    
    // Initialize the page
    initializeUserPage();
    
    // Apply theme styling
    setTimeout(applyPageTheme, 100);
    
    // Initialize modals
    setTimeout(initializeModals, 150);
    
    // Set up direct event handlers for UI elements
    setTimeout(function() {
      // Modal button handler
      const addUserBtn = document.getElementById('addUserBtn');
      if (addUserBtn) {
        addUserBtn.addEventListener('click', function() {
          if (window.userPageVue && typeof window.userPageVue.openAddUserModal === 'function') {
            window.userPageVue.openAddUserModal();
          } else {
            const modalElement = document.getElementById('userModal');
            if (modalElement && typeof bootstrap !== 'undefined') {
              try {
                const modal = new bootstrap.Modal(modalElement);
                modal.show();
              } catch (e) {
                console.error('Error showing modal:', e);
              }
            }
          }
        });
      }
      
      // Add direct click handlers for sort columns
      const sortColumns = {
        'sort-id': 'id',
        'sort-name': 'name', 
        'sort-email': 'email',
        'sort-role': 'role',
        'sort-active': 'active'
      };
      
      Object.entries(sortColumns).forEach(([elementId, columnKey]) => {
        const sortColumn = document.getElementById(elementId);
        if (sortColumn) {
          sortColumn.addEventListener('click', function() {
            if (window.userPageVue && typeof window.userPageVue.sortBy === 'function') {
              window.userPageVue.sortBy(columnKey);
            }
          });
        }
      });
      
      // Add listeners to all edit and delete buttons
      document.querySelectorAll('.edit-user-btn').forEach(btn => {
        btn.addEventListener('click', function() {
          const userIdStr = this.id.split('_')[1];
          const userId = parseInt(userIdStr, 10);
          
          if (window.userPageVue && userId) {
            const user = window.userPageVue.users.find(u => u.id === userId);
            if (user) {
              window.userPageVue.editUser(user);
            }
          }
        });
      });
      
      document.querySelectorAll('.delete-user-btn').forEach(btn => {
        btn.addEventListener('click', function() {
          const userIdStr = this.id.split('_')[1];
          const userId = parseInt(userIdStr, 10);
          
          if (window.userPageVue && userId) {
            const user = window.userPageVue.users.find(u => u.id === userId);
            if (user) {
              window.userPageVue.confirmDeleteUser(user);
            }
          }
        });
      });
    }, 500);
  }
});

// Create a function to apply sorting from saved preferences
window.applyUserTableSort = function() {
  // Read from localStorage
  const sortKey = localStorage.getItem('user-table-sort-key');
  const sortOrderStr = localStorage.getItem('user-table-sort-order');
  
  // Parse sort order
  const sortOrder = sortOrderStr === "-1" ? -1 : 1;
  
  // Apply to Vue instance if available
  if (window.userPageVue) {
    if (sortKey) {
      window.userPageVue.sortKey = sortKey;
      window.userPageVue.sortOrder = sortOrder;
    }
    
    // Apply sorting if method exists
    if (typeof window.userPageVue.applySorting === 'function') {
      window.userPageVue.applySorting();
    }
    
    // Force update to ensure the view reflects the current sort state
    window.userPageVue.$forceUpdate();
  }
  
  // Apply visual styling directly to DOM
  const sortColumns = document.querySelectorAll('.sortable');
  
  sortColumns.forEach(col => {
    col.classList.remove('active-sort', 'asc', 'desc');
  });
  
  if (sortKey) {
    const activeCol = document.getElementById(`sort-${sortKey}`);
    if (activeCol) {
      activeCol.classList.add('active-sort');
      activeCol.classList.add(sortOrder === -1 ? 'desc' : 'asc');
    }
  }
};

// Export for direct access
window.initializeUserPage = initializeUserPage;

