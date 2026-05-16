import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { authService } from '../services/authService';

const UserDashboard = () => {
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

  const handleLogout = () => {
    authService.logout();
    navigate('/login');
  };

  if (!user) return null;

  return (
    <div className="dashboard-layout">
      <aside className="sidebar">
        <div className="sidebar-header">
           <div className="sidebar-logo">🏭</div>
           <div className="sidebar-brand">WareFlow</div>
        </div>
        <nav className="sidebar-nav">
           <button className="nav-item active">📊 Tổng quan</button>
           <button className="nav-item">📦 Vật tư</button>
        </nav>
        <div className="sidebar-footer">
          <div className="user-info">
            <div className="user-avatar">{user.userName[0].toUpperCase()}</div>
            <div className="user-details">
              <div className="user-name">{user.userName}</div>
              <div className="user-role">Nhân viên</div>
            </div>
            <button className="logout-btn" onClick={handleLogout}>🚪</button>
          </div>
        </div>
      </aside>
      <main className="main-content">
        <header className="topbar">
          <span className="topbar-title">Khu vực nhân viên</span>
          <div className="topbar-right">
             <button 
               className="notification-bell" 
               onClick={() => setIsDarkMode(!isDarkMode)}
               title={isDarkMode ? "Chuyển sang chế độ sáng" : "Chuyển sang chế độ tối"}
               style={{ fontSize: '18px' }}
             >
               {isDarkMode ? '🌞' : '🌙'}
             </button>
          </div>
        </header>
        <div className="page-content">
           <div className="welcome-section">
             <h2>Chào mừng, {user.userName}!</h2>
             <p>Bạn đang truy cập với quyền Nhân viên.</p>
           </div>
        </div>
      </main>
    </div>
  );
};

export default UserDashboard;