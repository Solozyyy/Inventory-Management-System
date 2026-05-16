import api from '../api/axiosConfig';
import { jwtDecode } from 'jwt-decode';

interface LoginResponse {
  token: string;
}

export const authService = {
  login: async (credentials: any) => {
    const response = await api.post<LoginResponse>('/login', credentials);
    const { token } = response.data;
    localStorage.setItem('token', token);
    return token;
  },

  register: async (userData: any) => {
    return await api.post('/register', userData);
  },

  logout: () => {
    localStorage.removeItem('token');
  },

  getCurrentUser: () => {
    const token = localStorage.getItem('token');
    if (!token) return null;
    try {
      const decoded: any = jwtDecode(token);
      return {
        userName: decoded.sub, // sub thường là tên đăng nhập hoặc email
        role: decoded.role,
      };
    } catch {
      return null;
    }
  },

  isAuthenticated: () => {
    return !!localStorage.getItem('token');
  }
};
