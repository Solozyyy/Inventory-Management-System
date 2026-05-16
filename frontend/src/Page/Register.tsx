import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { authService } from '../services/authService';

const Register = () => {
  const [formData, setFormData] = useState({
    name: '',
    role: '',
    userName: '',
    password: '',
    confirmPassword: ''
  });
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState<{ text: string; type: 'success' | 'error' } | null>(null);
  const navigate = useNavigate();

  const handleRegister = async (e: React.FormEvent) => {
    e.preventDefault();
    if (formData.password !== formData.confirmPassword) {
      setMessage({ text: 'Mật khẩu xác nhận không khớp', type: 'error' });
      return;
    }

    setLoading(true);
    try {
      await authService.register(formData);
      setMessage({ text: 'Đăng ký thành công! Đang chuyển hướng...', type: 'success' });
      setTimeout(() => navigate('/login'), 2000);
    } catch (err: any) {
      setMessage({ text: err.response?.data || 'Đăng ký thất bại', type: 'error' });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-bg">
      <div className="auth-card">
        <div className="auth-logo">
          <div className="auth-logo-icon">🏭</div>
          <span className="auth-logo-text">WareFlow</span>
        </div>

        <h1 className="auth-title">Tạo tài khoản</h1>
        <form onSubmit={handleRegister}>
          <div className="form-group">
            <label>Họ và tên</label>
            <input 
              type="text" 
              required 
              value={formData.name}
              onChange={e => setFormData({...formData, name: e.target.value})}
            />
          </div>
          <div className="form-group">
            <label>Vai trò</label>
            <select 
              required 
              value={formData.role}
              onChange={e => setFormData({...formData, role: e.target.value})}
            >
              <option value="">-- Chọn vai trò --</option>
              <option value="ADMIN">Admin</option>
              <option value="EMPLOYEE">Nhân viên</option>
            </select>
          </div>
          <div className="form-group">
            <label>Tên đăng nhập</label>
            <input 
              type="text" 
              required 
              value={formData.userName}
              onChange={e => setFormData({...formData, userName: e.target.value})}
            />
          </div>
          <div className="form-group">
            <label>Mật khẩu</label>
            <input 
              type="password" 
              required 
              value={formData.password}
              onChange={e => setFormData({...formData, password: e.target.value})}
            />
          </div>
          <div className="form-group">
            <label>Xác nhận mật khẩu</label>
            <input 
              type="password" 
              required 
              value={formData.confirmPassword}
              onChange={e => setFormData({...formData, confirmPassword: e.target.value})}
            />
          </div>
          <button type="submit" className="btn-primary" disabled={loading}>
            {loading ? 'Đang đăng ký...' : 'Đăng ký'}
          </button>
        </form>

        {message && <div className={`message ${message.type}`}>{message.text}</div>}

        <div className="auth-switch">
          Đã có tài khoản?{' '}
          <button onClick={() => navigate('/login')}>Đăng nhập</button>
        </div>
      </div>
    </div>
  );
};

export default Register;
