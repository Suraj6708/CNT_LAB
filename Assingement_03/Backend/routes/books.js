const express = require("express");
const {
  getAllBooks,
  getBookById,
  createBook,
  updateBook,
  deleteBook,
  getGenres,
} = require("../controllers/bookController");
const { auth, requireRole } = require("../middleware/auth");

const router = express.Router();

// Public routes
router.get("/", getAllBooks);
router.get("/genres", getGenres);
router.get("/:id", getBookById);

// Protected routes (librarian only)
router.post("/", auth, requireRole(["librarian"]), createBook);
router.put("/:id", auth, requireRole(["librarian"]), updateBook);
router.delete("/:id", auth, requireRole(["librarian"]), deleteBook);

module.exports = router;
