const Book = require("../models/Book");

const getAllBooks = async (req, res) => {
  try {
    const {
      page = 1,
      limit = 10,
      genre,
      search,
      sortBy = "createdAt",
      sortOrder = "desc",
    } = req.query;

    // Build filter object
    const filter = { isAvailable: true };

    if (genre) {
      filter.genre = genre;
    }

    if (search) {
      filter.$text = { $search: search };
    }

    // Build sort object
    const sort = {};
    sort[sortBy] = sortOrder === "desc" ? -1 : 1;

    const books = await Book.find(filter)
      .populate("addedBy", "username firstName lastName")
      .sort(sort)
      .limit(limit * 1)
      .skip((page - 1) * limit);

    const total = await Book.countDocuments(filter);

    res.json({
      books,
      totalPages: Math.ceil(total / limit),
      currentPage: parseInt(page),
      totalBooks: total,
    });
  } catch (error) {
    console.error("Get all books error:", error);
    res.status(500).json({ message: "Server error" });
  }
};

const getBookById = async (req, res) => {
  try {
    const book = await Book.findById(req.params.id).populate(
      "addedBy",
      "username firstName lastName"
    );

    if (!book) {
      return res.status(404).json({ message: "Book not found" });
    }

    res.json(book);
  } catch (error) {
    console.error("Get book by ID error:", error);
    res.status(500).json({ message: "Server error" });
  }
};

const createBook = async (req, res) => {
  try {
    const {
      title,
      author,
      description,
      genre,
      isbn,
      price,
      stock,
      imageUrl,
      publishedDate,
      publisher,
      pages,
      language,
    } = req.body;

    // Check if ISBN already exists
    const existingBook = await Book.findOne({ isbn });
    if (existingBook) {
      return res
        .status(400)
        .json({ message: "Book with this ISBN already exists" });
    }

    const book = new Book({
      title,
      author,
      description,
      genre,
      isbn,
      price,
      stock,
      imageUrl,
      publishedDate,
      publisher,
      pages,
      language,
      addedBy: req.user._id,
    });

    await book.save();
    await book.populate("addedBy", "username firstName lastName");

    res.status(201).json({
      message: "Book created successfully",
      book,
    });
  } catch (error) {
    console.error("Create book error:", error);
    res.status(500).json({ message: "Server error" });
  }
};

const updateBook = async (req, res) => {
  try {
    const {
      title,
      author,
      description,
      genre,
      isbn,
      price,
      stock,
      imageUrl,
      publishedDate,
      publisher,
      pages,
      language,
      isAvailable,
    } = req.body;

    const book = await Book.findById(req.params.id);
    if (!book) {
      return res.status(404).json({ message: "Book not found" });
    }

    // Check if ISBN is being changed and if it already exists
    if (isbn && isbn !== book.isbn) {
      const existingBook = await Book.findOne({
        isbn,
        _id: { $ne: req.params.id },
      });
      if (existingBook) {
        return res
          .status(400)
          .json({ message: "Book with this ISBN already exists" });
      }
    }

    // Update book
    Object.assign(book, {
      title: title || book.title,
      author: author || book.author,
      description: description || book.description,
      genre: genre || book.genre,
      isbn: isbn || book.isbn,
      price: price !== undefined ? price : book.price,
      stock: stock !== undefined ? stock : book.stock,
      imageUrl: imageUrl || book.imageUrl,
      publishedDate: publishedDate || book.publishedDate,
      publisher: publisher || book.publisher,
      pages: pages || book.pages,
      language: language || book.language,
      isAvailable: isAvailable !== undefined ? isAvailable : book.isAvailable,
    });

    await book.save();
    await book.populate("addedBy", "username firstName lastName");

    res.json({
      message: "Book updated successfully",
      book,
    });
  } catch (error) {
    console.error("Update book error:", error);
    res.status(500).json({ message: "Server error" });
  }
};

const deleteBook = async (req, res) => {
  try {
    const book = await Book.findById(req.params.id);
    if (!book) {
      return res.status(404).json({ message: "Book not found" });
    }

    await Book.findByIdAndDelete(req.params.id);

    res.json({ message: "Book deleted successfully" });
  } catch (error) {
    console.error("Delete book error:", error);
    res.status(500).json({ message: "Server error" });
  }
};

const getGenres = async (req, res) => {
  try {
    const genres = await Book.distinct("genre");
    res.json(genres);
  } catch (error) {
    console.error("Get genres error:", error);
    res.status(500).json({ message: "Server error" });
  }
};

module.exports = {
  getAllBooks,
  getBookById,
  createBook,
  updateBook,
  deleteBook,
  getGenres,
};
