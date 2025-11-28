import React from "react";
import { Routes, Route } from "react-router-dom";
import AdminLayout from "./AdminLayout";
import AdminDashboard from "./AdminDashboard";
import UserManagement from "./UserManagement";
import BookManagement from "./BookManagement";
import AddBook from "./AddBook";
import EditBook from "./EditBook";

const Admin = () => {
  return (
    <AdminLayout>
      <Routes>
        <Route index element={<AdminDashboard />} />
        <Route path="users" element={<UserManagement />} />
        <Route path="books" element={<BookManagement />} />
        <Route path="books/add" element={<AddBook />} />
        <Route path="books/:id/edit" element={<EditBook />} />
      </Routes>
    </AdminLayout>
  );
};

export default Admin;
