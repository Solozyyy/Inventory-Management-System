import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { authService } from '../services/authService';

const Login = () => {
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState<{ text: string; type: 'success' | 'error' } | null>(null);
  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setMessage(null);
    try {
      await authService.login({ userName, password });
      
      setMessage({ text: 'Đăng nhập thành công! Đang chuyển hướng...', type: 'success' });
      
      const user = authService.getCurrentUser();
      
      setTimeout(() => {
        if (user?.role === 'ADMIN') {
          navigate('/admin');
        } else {
          navigate('/employee');
        }
      }, 1000);
    } catch (err: any) {
      console.error('Login error:', err);
      const errorMsg = err.response?.data || 'Sai tên đăng nhập hoặc mật khẩu';
      setMessage({ text: typeof errorMsg === 'string' ? errorMsg : 'Lỗi đăng nhập', type: 'error' });
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
        
        <h1 className="auth-title">Chào mừng trở lại</h1>
        <p className="auth-subtitle">Đăng nhập vào hệ thống quản lý kho</p>

        <form onSubmit={handleLogin}>
          <div className="form-group">
            <label htmlFor="userName">Tên đăng nhập</label>
            <div className="input-wrapper">
              <span className="input-icon">👤</span>
              <input
                id="userName"
                type="text"
                value={userName}
                onChange={e => setUserName(e.target.value)}
                placeholder="Nhập tên đăng nhập"
                required
              />
            </div>
          </div>

          <div className="form-group">
            <label htmlFor="password">Mật khẩu</label>
            <div className="input-wrapper">
              <span className="input-icon">🔒</span>
              <input
                id="password"
                type="password"
                value={password}
                onChange={e => setPassword(e.target.value)}
                placeholder="Nhập mật khẩu"
                required
              />
            </div>
          </div>

          <button type="submit" className="btn-primary" disabled={loading}>
            {loading ? 'Đang đăng nhập...' : 'Đăng nhập'}
          </button>
        </form>

        {message && <div className={`message ${message.type}`}>{message.text}</div>}

        <div className="auth-switch">
          Chưa có tài khoản?{' '}
          <button onClick={() => navigate('/register')}>Đăng ký ngay</button>
        </div>
      </div>
    </div>
  );
};

export default Login;
