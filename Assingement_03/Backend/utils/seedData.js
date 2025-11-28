const User = require("../models/User");
const Book = require("../models/Book");

const seedData = async () => {
  try {
    // Check if admin user already exists
    const adminExists = await User.findOne({ username: "admin" });

    if (!adminExists) {
      // Create default admin user
      const admin = new User({
        username: "admin",
        email: "admin@bookstore.com",
        password: "admin123", // This will be hashed automatically
        firstName: "Admin",
        lastName: "User",
        role: "librarian",
      });

      await admin.save();
      console.log(
        "✅ Default admin user created (username: admin, password: admin123)"
      );
    }

    // Check if books already exist
    const booksExist = await Book.countDocuments();

    if (booksExist === 0) {
      // Create dummy books
      const dummyBooks = [
        {
          title: "The Great Gatsby",
          author: "F. Scott Fitzgerald",
          description:
            "A classic American novel set in the Jazz Age, following the mysterious Jay Gatsby and his obsession with the beautiful Daisy Buchanan.",
          genre: "Fiction",
          isbn: "9780743273565",
          price: 12.99,
          stock: 50,
          imageUrl:
            "https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=400",
          publishedDate: new Date("1925-04-10"),
          publisher: "Scribner",
          pages: 180,
          language: "English",
          addedBy: adminExists
            ? adminExists._id
            : (await User.findOne({ username: "admin" }))._id,
        },
        {
          title: "To Kill a Mockingbird",
          author: "Harper Lee",
          description:
            "A gripping tale of racial injustice and childhood innocence in the American South during the 1930s.",
          genre: "Fiction",
          isbn: "9780061120084",
          price: 14.99,
          stock: 35,
          imageUrl:
            "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400",
          publishedDate: new Date("1960-07-11"),
          publisher: "J.B. Lippincott & Co.",
          pages: 281,
          language: "English",
          addedBy: adminExists
            ? adminExists._id
            : (await User.findOne({ username: "admin" }))._id,
        },
        {
          title: "1984",
          author: "George Orwell",
          description:
            "A dystopian social science fiction novel about totalitarian control and surveillance in a society.",
          genre: "Science Fiction",
          isbn: "9780451524935",
          price: 13.99,
          stock: 40,
          imageUrl:
            "https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=400",
          publishedDate: new Date("1949-06-08"),
          publisher: "Secker & Warburg",
          pages: 328,
          language: "English",
          addedBy: adminExists
            ? adminExists._id
            : (await User.findOne({ username: "admin" }))._id,
        },
        {
          title: "Pride and Prejudice",
          author: "Jane Austen",
          description:
            "A romantic novel that critiques the British landed gentry of the early 19th century.",
          genre: "Romance",
          isbn: "9780141439518",
          price: 11.99,
          stock: 45,
          imageUrl:
            "https://images.unsplash.com/photo-1543002588-bfa74002ed7e?w=400",
          publishedDate: new Date("1813-01-28"),
          publisher: "T. Egerton",
          pages: 432,
          language: "English",
          addedBy: adminExists
            ? adminExists._id
            : (await User.findOne({ username: "admin" }))._id,
        },
        {
          title: "The Hobbit",
          author: "J.R.R. Tolkien",
          description:
            "A fantasy novel about a hobbit who goes on an unexpected journey with dwarves and a wizard.",
          genre: "Fantasy",
          isbn: "9780547928227",
          price: 15.99,
          stock: 30,
          imageUrl:
            "https://images.unsplash.com/photo-1578662996442-48f60103fc96?w=400",
          publishedDate: new Date("1937-09-21"),
          publisher: "George Allen & Unwin",
          pages: 310,
          language: "English",
          addedBy: adminExists
            ? adminExists._id
            : (await User.findOne({ username: "admin" }))._id,
        },
        {
          title: "The Catcher in the Rye",
          author: "J.D. Salinger",
          description:
            "A coming-of-age story about teenage rebellion and alienation in post-World War II America.",
          genre: "Fiction",
          isbn: "9780316769174",
          price: 13.49,
          stock: 25,
          imageUrl:
            "https://images.unsplash.com/photo-1512820790803-83ca734da794?w=400",
          publishedDate: new Date("1951-07-16"),
          publisher: "Little, Brown and Company",
          pages: 277,
          language: "English",
          addedBy: adminExists
            ? adminExists._id
            : (await User.findOne({ username: "admin" }))._id,
        },
        {
          title: "The Da Vinci Code",
          author: "Dan Brown",
          description:
            "A mystery thriller novel about a symbologist and a cryptographer who solve a murder in the Louvre.",
          genre: "Mystery",
          isbn: "9780307474278",
          price: 16.99,
          stock: 20,
          imageUrl:
            "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400",
          publishedDate: new Date("2003-03-18"),
          publisher: "Doubleday",
          pages: 689,
          language: "English",
          addedBy: adminExists
            ? adminExists._id
            : (await User.findOne({ username: "admin" }))._id,
        },
        {
          title: "The Seven Habits of Highly Effective People",
          author: "Stephen R. Covey",
          description:
            "A business and self-help book that presents a principle-centered approach to solving personal and professional problems.",
          genre: "Self-Help",
          isbn: "9781982137274",
          price: 18.99,
          stock: 15,
          imageUrl:
            "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?w=400",
          publishedDate: new Date("1989-08-15"),
          publisher: "Free Press",
          pages: 432,
          language: "English",
          addedBy: adminExists
            ? adminExists._id
            : (await User.findOne({ username: "admin" }))._id,
        },
        {
          title: "Sapiens: A Brief History of Humankind",
          author: "Yuval Noah Harari",
          description:
            "An exploration of how Homo sapiens came to dominate the world through three major revolutions.",
          genre: "History",
          isbn: "9780062316097",
          price: 19.99,
          stock: 22,
          imageUrl:
            "https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=400",
          publishedDate: new Date("2011-01-01"),
          publisher: "Harper",
          pages: 443,
          language: "English",
          addedBy: adminExists
            ? adminExists._id
            : (await User.findOne({ username: "admin" }))._id,
        },
        {
          title: "Clean Code: A Handbook of Agile Software Craftsmanship",
          author: "Robert C. Martin",
          description:
            "A software engineering book that discusses principles for writing clean, maintainable code.",
          genre: "Technology",
          isbn: "9780132350884",
          price: 49.99,
          stock: 18,
          imageUrl:
            "https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=400",
          publishedDate: new Date("2008-08-01"),
          publisher: "Prentice Hall",
          pages: 464,
          language: "English",
          addedBy: adminExists
            ? adminExists._id
            : (await User.findOne({ username: "admin" }))._id,
        },
      ];

      await Book.insertMany(dummyBooks);
      console.log("✅ Dummy books created successfully");
    }

    console.log("✅ Database seeding completed");
  } catch (error) {
    console.error("❌ Error seeding database:", error);
  }
};

module.exports = seedData;
