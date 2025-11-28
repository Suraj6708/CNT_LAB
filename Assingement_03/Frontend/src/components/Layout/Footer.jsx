import React from "react";

const Footer = () => {
  return (
    <footer className=" bg-gray-800 text-white pt-8 mt-8">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-8">
          {/* Company Info */}
          <div className="col-span-1 md:col-span-2">
            <div className="flex items-center space-x-2 mb-4">
              <div className="w-8 h-8 bg-blue-600 rounded-full flex items-center justify-center">
                <span className="text-white font-bold text-lg">📚</span>
              </div>
              <span className="text-xl font-bold">BookStore</span>
            </div>
            <p className="text-gray-300 mb-4">
              Your one-stop destination for discovering and purchasing amazing
              books. From classic literature to modern bestsellers, we have
              something for every reader.
            </p>
          </div>

          {/* Quick Links */}
          <div>
            <h3 className="text-lg font-semibold mb-4">Quick Links</h3>
            <ul className="space-y-2">
              <li>
                <a
                  href="/"
                  className="text-gray-300 hover:text-white transition-colors"
                >
                  Home
                </a>
              </li>
              <li>
                <a
                  href="/catalog"
                  className="text-gray-300 hover:text-white transition-colors"
                >
                  Browse Books
                </a>
              </li>
              <li>
                <a
                  href="/login"
                  className="text-gray-300 hover:text-white transition-colors"
                >
                  Login
                </a>
              </li>
              <li>
                <a
                  href="/register"
                  className="text-gray-300 hover:text-white transition-colors"
                >
                  Register
                </a>
              </li>
            </ul>
          </div>

          {/* Contact Info */}
          <div>
            <h3 className="text-lg font-semibold mb-4">Contact</h3>
            <div className="space-y-2 text-gray-300">
              <p>📧 info@bookstore.com</p>
              <p>📞 +1 (555) 123-4567</p>
              <p>📍 123 Book Street, Library City</p>
            </div>
          </div>
        </div>

        <div className="border-t border-gray-700 mt-8 pt-8 text-center">
          <p className="text-gray-300">
            © 2024 BookStore. All rights reserved. Built with React & Node.js
          </p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
