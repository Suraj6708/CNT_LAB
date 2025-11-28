import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import Layout from "./components/Layout/Layout.jsx";
import ProtectedRoute from "./components/ProtectedRoute.jsx";
import Home from "./pages/Home.jsx";
import Login from "./pages/Login.jsx";
import Register from "./pages/Register.jsx";
import Catalog from "./pages/Catalog.jsx";
import BookDetail from "./pages/BookDetail.jsx";
import Admin from "./pages/Admin/index.jsx";
import "./App.css";
import { useAuth } from "./contexts/AuthContext.jsx";

function App() {
  const { isAuthenticated } = useAuth();
  return (
    <Routes>
      {/* Public Routes */}
      <Route
        path="/"
        element={
          <Layout>
            <Home />
          </Layout>
        }
      />
      <Route
        path="/login"
        element={isAuthenticated() ? <Navigate to="/" replace /> : <Login />}
      />
      <Route
        path="/register"
        element={isAuthenticated() ? <Navigate to="/" replace /> : <Register />}
      />
      <Route
        path="/catalog"
        element={
          <Layout>
            <Catalog />
          </Layout>
        }
      />
      <Route
        path="/catalog/:id"
        element={
          <Layout>
            <BookDetail />
          </Layout>
        }
      />

      {/* Admin Routes */}
      <Route
        path="/admin/*"
        element={
          <ProtectedRoute requireAdmin={true}>
            <Admin />
          </ProtectedRoute>
        }
      />
    </Routes>
  );
}

export default App;
