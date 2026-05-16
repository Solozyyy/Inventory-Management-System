import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './Page/Login.tsx';
import Register from './Page/Register.tsx';
import AdminDashboard from './Page/AdminDashboard.tsx';
import UserDashboard from './Page/UserDashboard.tsx';
import ProtectedRoute from './components/ProtectedRoute.tsx';
import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        {/* Public Routes */}
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        {/* Protected Admin Routes */}
        <Route element={<ProtectedRoute allowedRoles={['ADMIN']} />}>
          <Route path="/admin" element={<AdminDashboard />} />
        </Route>

        {/* Protected Employee Routes */}
        <Route element={<ProtectedRoute allowedRoles={['EMPLOYEE']} />}>
          <Route path="/employee" element={<UserDashboard />} />
        </Route>

        {/* Default Redirect */}
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="*" element={<Navigate to="/login" replace />} />
      </Routes>
    </Router>
  );
}

export default App;
