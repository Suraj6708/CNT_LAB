const mongoose = require("mongoose");

const bookSchema = new mongoose.Schema({
  title: {
    type: String,
    required: true,
    trim: true,
  },
  author: {
    type: String,
    required: true,
    trim: true,
  },
  description: {
    type: String,
    required: true,
    trim: true,
  },
  genre: {
    type: String,
    required: true,
    trim: true,
    enum: [
      "Fiction",
      "Non-Fiction",
      "Science Fiction",
      "Fantasy",
      "Mystery",
      "Romance",
      "Thriller",
      "Biography",
      "History",
      "Self-Help",
      "Technology",
      "Philosophy",
    ],
  },
  isbn: {
    type: String,
    required: true,
    unique: true,
    trim: true,
  },
  price: {
    type: Number,
    required: true,
    min: 0,
  },
  stock: {
    type: Number,
    required: true,
    min: 0,
    default: 0,
  },
  imageUrl: {
    type: String,
    required: true,
  },
  publishedDate: {
    type: Date,
    required: true,
  },
  publisher: {
    type: String,
    required: true,
    trim: true,
  },
  pages: {
    type: Number,
    min: 1,
  },
  language: {
    type: String,
    default: "English",
    trim: true,
  },
  isAvailable: {
    type: Boolean,
    default: true,
  },
  addedBy: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "User",
    required: true,
  },
  createdAt: {
    type: Date,
    default: Date.now,
  },
  updatedAt: {
    type: Date,
    default: Date.now,
  },
});

// Update timestamp on save
bookSchema.pre("save", function (next) {
  this.updatedAt = Date.now();
  next();
});

// Index for search functionality
bookSchema.index(
  { title: "text", author: "text", description: "text" },
  { default_language: "none" }
);

module.exports = mongoose.model("Book", bookSchema);
