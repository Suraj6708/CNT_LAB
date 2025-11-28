const express = require("express");
const {
  getAllUsers,
  getUserById,
  updateUser,
  deleteUser,
  getDashboardStats,
  getAllBooksAdmin,
} = require("../controllers/adminController");
const { auth, requireRole } = require("../middleware/auth");

const router = express.Router();

// All admin routes require authentication and admin role
router.use(auth);
router.use(requireRole(["librarian"])); // Only librarians can access admin features

// Dashboard
router.get("/dashboard", getDashboardStats);

// User management
router.get("/users", getAllUsers);
router.get("/users/:id", getUserById);
router.put("/users/:id", updateUser);
router.delete("/users/:id", deleteUser);

// Book management (admin view)
router.get("/books", getAllBooksAdmin);

module.exports = router;
