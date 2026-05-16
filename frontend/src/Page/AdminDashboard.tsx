import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { authService } from '../services/authService';

// Import sub-pages
import DashboardOverview from './Admin/DashboardOverview';
import MaterialsPage from './Admin/MaterialsPage';
import MembersPage from './Admin/MembersPage';
import ImportPage from './Admin/ImportPage';
import SalesPage from './Admin/SalesPage';
import SuppliersPage from './Admin/SuppliersPage';

type ActiveMenu = 'dashboard' | 'materials' | 'import' | 'sales' | 'members' | 'suppliers'

const AdminDashboard = () => {
  const [active, setActive] = useState<ActiveMenu>(() => {
    const saved = localStorage.getItem('adminActiveMenu');
    return (saved as ActiveMenu) || 'dashboard';
  });
  
  const [user, setUser] = useState<any>(null);
  const [isDarkMode, setIsDarkMode] = useState(() => {
    const saved = localStorage.getItem('theme');
    return saved !== 'light'; 
  });
  
  const navigate = useNavigate();

  useEffect(() => {
    if (isDarkMode) {
      document.documentElement.classList.remove('light-theme');
      localStorage.setItem('theme', 'dark');
    } else {
      document.documentElement.classList.add('light-theme');
      localStorage.setItem('theme', 'light');
    }
  }, [isDarkMode]);

  useEffect(() => {
    const currentUser = authService.getCurrentUser();
    if (!currentUser) {
      navigate('/login');
    } else {
      setUser(currentUser);
    }
  }, [navigate]);

  useEffect(() => {
    localStorage.setItem('adminActiveMenu', active);
  }, [active]);

  const handleLogout = () => {
    authService.logout();
    navigate('/login');
  };

  if (!user) return null;

  const navItems: { key: ActiveMenu; icon: string; label: string }[] = [
    { key: 'dashboard', icon: '📊', label: 'Tổng quan' },
    { key: 'materials', icon: '📦', label: 'Quản lý vật tư' },
    { key: 'import',    icon: '📥', label: 'Nhập kho' },
    { key: 'sales',     icon: '📤', label: 'Bán hàng' },
    { key: 'members',   icon: '👥', label: 'Nhân viên' },
    { key: 'suppliers', icon: '🏢', label: 'Nhà cung cấp' },
  ];

  return (
    <div className="dashboard-layout">
      {/* ── Sidebar ── */}
      <aside className="sidebar">
        <div className="sidebar-header">
          <div className="sidebar-logo">🏭</div>
          <div className="sidebar-brand">
            WareFlow
            <small>Admin Portal</small>
          </div>
        </div>

        <nav className="sidebar-nav">
          <div className="nav-section-label">Menu chính</div>
          {navItems.map(item => (
            <button
              key={item.key}
              className={`nav-item ${active === item.key ? 'active' : ''}`}
              onClick={() => setActive(item.key)}
            >
              <span className="nav-icon">{item.icon}</span>
              {item.label}
            </button>
          ))}
        </nav>

        <div className="sidebar-footer">
          <div className="user-info">
            <div className="user-avatar">
              {user.userName.charAt(0).toUpperCase()}
            </div>
            <div className="user-details">
              <div className="user-name">{user.userName}</div>
              <div className="user-role admin">Admin</div>
            </div>
            <button className="logout-btn" onClick={handleLogout} title="Đăng xuất">🚪</button>
          </div>
        </div>
      </aside>

      {/* ── Main Content ── */}
      <main className="main-content">
        <header className="topbar">
          <span className="topbar-title">
            {navItems.find(i => i.key === active)?.label || 'Dashboard'}
          </span>
          <div className="topbar-right">
             <button 
               className="notification-bell" 
               onClick={() => setIsDarkMode(!isDarkMode)}
               title={isDarkMode ? "Chuyển sang chế độ sáng" : "Chuyển sang chế độ tối"}
               style={{ fontSize: '18px' }}
             >
               {isDarkMode ? '🌞' : '🌙'}
             </button>
             <div className="notification-bell">🔔</div>
             <span className="topbar-badge admin">👑 Admin Mode</span>
          </div>
        </header>

        <div className="page-content">
          {active === 'dashboard' && <DashboardOverview user={user} />}
          {active === 'materials' && <MaterialsPage />}
          {active === 'members' && <MembersPage />}
          {active === 'import' && <ImportPage />}
          {active === 'sales' && <SalesPage />}
          {active === 'suppliers' && <SuppliersPage />}
        </div>
      </main>
    </div>
  );
};

export default AdminDashboard;