import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { booksAPI } from "../services/api";
import { useAuth } from "../contexts/AuthContext";

const Home = () => {
  const [featuredBooks, setFeaturedBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const { isAuthenticated } = useAuth();

  useEffect(() => {
    const fetchFeaturedBooks = async () => {
      try {
        const response = await booksAPI.getAll({
          limit: 6,
          sortBy: "createdAt",
          sortOrder: "desc",
        });
        setFeaturedBooks(response.data.books);
      } catch (error) {
        console.error("Error fetching featured books:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchFeaturedBooks();
  }, []);

  if (loading) {
    return (
      <div className="flex justify-center items-center min-h-64">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
      </div>
    );
  }

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      {/* Hero Section */}
      <div className="bg-gradient-to-r from-blue-600 to-purple-600 rounded-lg p-8 mb-12 text-white">
        <div className="max-w-3xl">
          <h1 className="text-4xl md:text-6xl font-bold mb-4">
            Welcome to BookStore
          </h1>
          <p className="text-xl mb-6 opacity-90">
            Discover your next favorite book from our extensive collection of
            fiction, non-fiction, and everything in between.
          </p>
          <div className="flex flex-col sm:flex-row gap-4">
            <Link
              to="/catalog"
              className="bg-white text-blue-600 px-6 py-3 rounded-lg font-semibold hover:bg-gray-100 transition-colors text-center"
            >
              Browse Books
            </Link>
            <Link
              to="/register"
              className="border-2 border-white text-white px-6 py-3 rounded-lg font-semibold hover:bg-white hover:text-blue-600 transition-colors text-center"
            >
              Join Now
            </Link>
          </div>
        </div>
      </div>

      {/* Features Section */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-8 mb-12">
        <div className="text-center p-6">
          <div className="w-16 h-16 bg-blue-100 rounded-full flex items-center justify-center mx-auto mb-4">
            <span className="text-2xl">📚</span>
          </div>
          <h3 className="text-xl font-semibold mb-2">Extensive Collection</h3>
          <p className="text-gray-600">
            Browse through thousands of books across various genres and
            categories.
          </p>
        </div>
        <div className="text-center p-6">
          <div className="w-16 h-16 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-4">
            <span className="text-2xl">🔍</span>
          </div>
          <h3 className="text-xl font-semibold mb-2">Easy Search</h3>
          <p className="text-gray-600">
            Find exactly what you're looking for with our advanced search and
            filter options.
          </p>
        </div>
        <div className="text-center p-6">
          <div className="w-16 h-16 bg-purple-100 rounded-full flex items-center justify-center mx-auto mb-4">
            <span className="text-2xl">⚡</span>
          </div>
          <h3 className="text-xl font-semibold mb-2">Fast & Secure</h3>
          <p className="text-gray-600">
            Enjoy a seamless and secure shopping experience with fast loading
            times.
          </p>
        </div>
      </div>

      {/* Featured Books Section */}
      <div className="mb-12">
        <div className="flex justify-between items-center mb-8">
          <h2 className="text-3xl font-bold">Featured Books</h2>
          <Link
            to="/catalog"
            className="text-blue-600 hover:text-blue-800 font-semibold"
          >
            View All →
          </Link>
        </div>

        {featuredBooks.length > 0 ? (
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
            {featuredBooks.map((book) => (
              <div
                key={book._id}
                className="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition-shadow"
              >
                <div className="">
                  <img
                    src={book.imageUrl}
                    alt={book.title}
                    className="w-full h-64 object-cover"
                  />
                </div>
                <div className="p-4">
                  <h3 className="font-semibold text-lg mb-2 line-clamp-2">
                    {book.title}
                  </h3>
                  <p className="text-gray-600 mb-2">by {book.author}</p>
                  <div className="flex justify-between items-center">
                    <span className="text-blue-600 font-semibold">
                      Rs {book.price}
                    </span>
                    <span className="text-sm text-gray-500 bg-gray-100 px-2 py-1 rounded">
                      {book.genre}
                    </span>
                  </div>
                  <Link
                    to={`/catalog/${book._id}`}
                    className="block w-full mt-3 bg-blue-600 text-white text-center py-2 rounded hover:bg-blue-700 transition-colors"
                  >
                    View Details
                  </Link>
                </div>
              </div>
            ))}
          </div>
        ) : (
          <div className="text-center py-12">
            <p className="text-gray-500 text-lg">
              No books available at the moment.
            </p>
          </div>
        )}
      </div>

      {/* Call to Action */}
      {!isAuthenticated() ? (
        <div className="bg-gray-50 rounded-lg p-8 text-center">
          <h2 className="text-2xl font-bold mb-4">Ready to Start Reading?</h2>
          <p className="text-gray-600 mb-6">
            Join thousands of book lovers who trust BookStore for their reading
            needs.
          </p>
          <Link
            to="/register"
            className="inline-block bg-blue-600 text-white px-8 py-3 rounded-lg font-semibold hover:bg-blue-700 transition-colors"
          >
            Create Your Account
          </Link>
        </div>
      ) : null}
    </div>
  );
};

export default Home;
