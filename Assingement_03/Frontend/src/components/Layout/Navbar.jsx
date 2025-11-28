import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../../contexts/AuthContext";

const Navbar = () => {
  const { user, logout, isAdmin } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  return (
    <nav className="bg-blue-600 text-white shadow-lg relative z-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          {/* Logo */}
          <Link to="/" className="flex items-center space-x-2">
            <div className="w-8 h-8 bg-white rounded-full flex items-center justify-center">
              <span className="text-blue-600 font-bold text-lg">📚</span>
            </div>
            <span className="text-xl font-bold">BookStore</span>
          </Link>

          {/* Navigation Links */}
          <div className="flex items-center space-x-8">
            <Link
              to="/"
              className="hover:text-blue-200 transition-colors duration-200"
            >
              Home
            </Link>
            <Link
              to="/catalog"
              className="hover:text-blue-200 cursor-pointer transition-colors duration-200"
            >
              Catalog
            </Link>

            {/* Admin Links */}
            {isAdmin() && (
              <Link
                to="/admin"
                className="hover:text-blue-200 transition-colors duration-200"
              >
                Admin Panel
              </Link>
            )}

            {/* User Section */}
            <div className="flex items-center space-x-4">
              {user ? (
                <>
                  <span className="text-sm">Welcome, {user.firstName}!</span>
                  <div className="flex items-center space-x-2">
                    <span className="text-xs bg-blue-500 px-2 py-1 rounded">
                      {user.role === "librarian" ? "Librarian" : "User"}
                    </span>
                    <button
                      onClick={handleLogout}
                      className="bg-red-500 hover:bg-red-600 px-3 py-1 rounded text-sm transition-colors duration-200"
                    >
                      Logout
                    </button>
                  </div>
                </>
              ) : (
                <div className="flex items-center space-x-2">
                  <Link
                    to="/login"
                    className="bg-blue-500 hover:bg-blue-700 px-3 py-1 rounded text-sm transition-colors duration-200"
                  >
                    Login
                  </Link>
                  <Link
                    to="/register"
                    className="bg-green-500 hover:bg-green-600 px-3 py-1 rounded text-sm transition-colors duration-200"
                  >
                    Register
                  </Link>
                </div>
              )}
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
