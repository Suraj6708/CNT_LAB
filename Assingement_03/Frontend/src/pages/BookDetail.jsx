import React, { useState, useEffect } from "react";
import { useParams, Link, useNavigate } from "react-router-dom";
import { booksAPI } from "../services/api";
import { useAuth } from "../contexts/AuthContext";

const BookDetail = () => {
  const { id } = useParams();
  const { user, isAdmin } = useAuth();
  const navigate = useNavigate();
  const [book, setBook] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchBook();
  }, [id]);

  const fetchBook = async () => {
    try {
      const response = await booksAPI.getById(id);
      setBook(response.data);
    } catch (error) {
      setError("Book not found");
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async () => {
    if (!isAdmin()) return;

    if (window.confirm("Are you sure you want to delete this book?")) {
      try {
        await booksAPI.delete(id);
        navigate("/catalog");
      } catch (error) {
        console.error("Error deleting book:", error);
        alert("Failed to delete book");
      }
    }
  };

  if (loading) {
    return (
      <div className="flex justify-center items-center min-h-64">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
      </div>
    );
  }

  if (error || !book) {
    return (
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="text-center">
          <h1 className="text-2xl font-bold text-gray-900 mb-4">
            Book Not Found
          </h1>
          <p className="text-gray-600 mb-6">
            The book you're looking for doesn't exist.
          </p>
          <Link
            to="/catalog"
            className="bg-blue-600 text-white px-6 py-2 rounded-md hover:bg-blue-700 transition-colors"
          >
            Back to Catalog
          </Link>
        </div>
      </div>
    );
  }

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      {/* Breadcrumb */}
      <nav className="mb-8">
        <ol className="flex items-center space-x-2 text-sm">
          <li>
            <Link to="/" className="text-blue-600 hover:text-blue-800">
              Home
            </Link>
          </li>
          <li className="text-gray-500">/</li>
          <li>
            <Link to="/catalog" className="text-blue-600 hover:text-blue-800">
              Catalog
            </Link>
          </li>
          <li className="text-gray-500">/</li>
          <li className="text-gray-900 font-medium">{book.title}</li>
        </ol>
      </nav>

      <div className="bg-white rounded-lg shadow-lg overflow-hidden">
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8 p-8">
          {/* Book Image */}
          <div>
            <img
              src={book.imageUrl}
              alt={book.title}
              className="w-full h-96 object-cover rounded-lg"
            />
          </div>

          {/* Book Details */}
          <div>
            <div className="mb-6">
              <h1 className="text-3xl font-bold text-gray-900 mb-2">
                {book.title}
              </h1>
              <p className="text-xl text-gray-600 mb-4">by {book.author}</p>

              <div className="flex items-center space-x-4 mb-4">
                <span className="bg-blue-100 text-blue-800 px-3 py-1 rounded-full text-sm font-medium">
                  {book.genre}
                </span>
                <span className="text-green-600 font-semibold text-xl">
                  Rs {book.price}
                </span>
                <span className="text-gray-500">Stock: {book.stock}</span>
              </div>
            </div>

            {/* Description */}
            <div className="mb-6">
              <h3 className="text-lg font-semibold mb-2">Description</h3>
              <p className="text-gray-700 leading-relaxed">
                {book.description}
              </p>
            </div>

            {/* Book Details */}
            <div className="mb-6">
              <h3 className="text-lg font-semibold mb-4">Book Details</h3>
              <div className="grid grid-cols-2 gap-4 text-sm">
                <div>
                  <span className="font-medium text-gray-600">Publisher:</span>
                  <p className="text-gray-900">{book.publisher}</p>
                </div>
                <div>
                  <span className="font-medium text-gray-600">Published:</span>
                  <p className="text-gray-900">
                    {new Date(book.publishedDate).toLocaleDateString()}
                  </p>
                </div>
                <div>
                  <span className="font-medium text-gray-600">Pages:</span>
                  <p className="text-gray-900">{book.pages}</p>
                </div>
                <div>
                  <span className="font-medium text-gray-600">Language:</span>
                  <p className="text-gray-900">{book.language}</p>
                </div>
                <div>
                  <span className="font-medium text-gray-600">ISBN:</span>
                  <p className="text-gray-900 font-mono">{book.isbn}</p>
                </div>
                <div>
                  <span className="font-medium text-gray-600">Added by:</span>
                  <p className="text-gray-900">
                    {book.addedBy?.firstName} {book.addedBy?.lastName}
                  </p>
                </div>
              </div>
            </div>

            {/* Actions */}
            <div className="flex flex-col sm:flex-row gap-4">
              {isAdmin() && (
                <>
                  <Link
                    to={`/admin/books/${book._id}/edit`}
                    className="flex-1 bg-yellow-600 text-white py-3 px-6 rounded-lg font-semibold hover:bg-yellow-700 transition-colors text-center"
                  >
                    Edit Book
                  </Link>
                  <button
                    onClick={handleDelete}
                    className="flex-1 bg-red-600 text-white py-3 px-6 rounded-lg font-semibold hover:bg-red-700 transition-colors"
                  >
                    Delete Book
                  </button>
                </>
              )}
            </div>
          </div>
        </div>
      </div>

      {/* Related Books Section */}
      <div className="mt-12">
        <h2 className="text-2xl font-bold text-gray-900 mb-6">
          More Books by {book.author}
        </h2>
        <div className="text-center py-8 text-gray-500">
          <p>Related books feature would be implemented here</p>
        </div>
      </div>
    </div>
  );
};

export default BookDetail;
