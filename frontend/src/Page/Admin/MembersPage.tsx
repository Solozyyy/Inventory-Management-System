import { useState, useEffect } from 'react';
import { adminService } from '../../services/adminService';

const MembersPage = () => {
  const [members, setMembers] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    loadMembers();
  }, []);

  const loadMembers = async () => {
    setLoading(true);
    try {
      const res = await adminService.getAllMembers();
      setMembers(res.data);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="card">
      <div className="card-header">
        <span className="card-title">👥 Danh sách nhân viên</span>
        <button className="btn-sm primary">+ Thêm mới</button>
      </div>
      <div className="card-body">
        {loading ? (
          <div className="loading-spinner">Đang tải...</div>
        ) : (
          <table className="data-table">
            <thead>
              <tr>
                <th>Tên đăng nhập</th>
                <th>Vai trò</th>
                <th>Hành động</th>
              </tr>
            </thead>
            <tbody>
              {members.map((m: any, i: number) => (
                <tr key={i}>
                  <td>{m.userName}</td>
                  <td><span className="badge info">{m.role}</span></td>
                  <td>
                    <button className="btn-icon">✏️</button>
                    <button className="btn-icon danger">🗑️</button>
                  </td>
                </tr>
              ))}
              {members.length === 0 && (
                <tr>
                  <td colSpan={3} style={{textAlign: 'center', padding: '2rem'}}>Không có dữ liệu</td>
                </tr>
              )}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default MembersPage;
