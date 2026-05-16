const DashboardOverview = ({ user }: { user: any }) => (
  <div className="welcome-section">
    <div className="page-header">
      <h2>Xin chào, {user.userName}! 👋</h2>
      <p>Chào mừng bạn quay trở lại với trang quản trị WareFlow.</p>
    </div>
    <div className="stats-grid">
      <StatCard icon="📦" label="Tổng sản phẩm" value="1,248" color="blue" />
      <StatCard icon="📈" label="Doanh thu" value="45.2M" color="green" />
      <StatCard icon="👥" label="Thành viên" value="12" color="purple" />
      <StatCard icon="⚠️" label="Cảnh báo" value="3" color="orange" />
    </div>
  </div>
);

const StatCard = ({ icon, label, value, color }: any) => (
  <div className={`stat-card ${color}`}>
    <div className="stat-icon">{icon}</div>
    <div className="stat-info">
      <div className="stat-label">{label}</div>
      <div className="stat-value">{value}</div>
    </div>
  </div>
);

export default DashboardOverview;
