import { useState, useEffect } from 'react';
import { adminService } from '../../services/adminService';

const MaterialsPage = () => {
  const [data, setData] = useState<any>({ content: [], last: true, first: true, number: 0 });
  const [loading, setLoading] = useState(false);
  const [page, setPage] = useState(0);
  const [search, setSearch] = useState('');
  const size = 10;

  useEffect(() => {
    loadMaterials(page, search);
  }, [page]);

  const loadMaterials = async (pageNum: number, name: string) => {
    setLoading(true);
    try {
      const res = await adminService.getAllMaterials(pageNum, size, name);
      setData(res.data);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const onSearch = () => {
    setPage(0);
    loadMaterials(0, search);
  };

  return (
    <div className="card">
      <div className="card-header">
        <div style={{ display: 'flex', alignItems: 'center', gap: '15px' }}>
          <span className="card-title">📦 Quản lý vật tư</span>
          <div className="search-box">
            <input 
              type="text" 
              placeholder="Tìm theo tên..." 
              value={search}
              onChange={(e) => setSearch(e.target.value)}
              onKeyDown={(e) => e.key === 'Enter' && onSearch()}
              className="search-input"
            />
            <button className="btn-sm outline" onClick={onSearch}>🔍</button>
          </div>
        </div>
        <button className="btn-sm primary">+ Thêm vật tư</button>
      </div>
      <div className="card-body">
        {loading ? (
          <div className="loading-spinner">Đang tải dữ liệu...</div>
        ) : (
          <>
            <table className="data-table">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Tên vật tư</th>
                  <th>Loại</th>
                  <th>Đơn giá</th>
                  <th>Hành động</th>
                </tr>
              </thead>
              <tbody>
                {data.content.map((m: any) => (
                  <tr key={m.id}>
                    <td style={{ color: 'var(--text-muted)' }}>#{m.id}</td>
                    <td style={{ fontWeight: 600 }}>{m.name}</td>
                    <td style={{ color: 'var(--text-secondary)' }}>{m.type}</td>
                    <td>{m.price?.toLocaleString()} đ</td>
                    <td>
                      <button className="btn-icon">✏️</button>
                      <button className="btn-icon danger">🗑️</button>
                    </td>
                  </tr>
                ))}
                {data.content.length === 0 && (
                  <tr>
                    <td colSpan={5} style={{textAlign: 'center', padding: '3rem'}}>Không tìm thấy vật tư nào</td>
                  </tr>
                )}
              </tbody>
            </table>
            
            <div className="pagination-controls">
              <span className="page-info">
                Trang <strong>{page + 1}</strong>
              </span>
              <div className="pagination-buttons">
                <button 
                  className="btn-sm outline" 
                  disabled={data.first || loading}
                  onClick={() => setPage(p => Math.max(0, p - 1))}
                >
                  ◀ Trang trước
                </button>
                <button 
                  className="btn-sm outline" 
                  disabled={data.last || loading}
                  onClick={() => setPage(p => p + 1)}
                >
                  Trang sau ▶
                </button>
              </div>
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default MaterialsPage;
