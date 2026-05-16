import api from '../api/axiosConfig';

export const adminService = {
  // Quản lý nhân viên
  getAllMembers: () => api.get('/admin/members'),
  createMember: (data: any) => api.post('/admin/member', data),
  
  // Quản lý vật tư
  getAllMaterials: (page: number, size: number, name = '') => 
    api.get(`/material?page=${page}&size=${size}&name=${name}`),
  
  // Dashboard stats
  getStats: () => api.get('/admin/stats'),
};
