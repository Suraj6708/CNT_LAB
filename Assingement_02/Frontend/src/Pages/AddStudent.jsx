import { useState } from "react";

export default function AddStudent() {
  const [form, setForm] = useState({
    rollNo: "",
    firstName: "",
    lastName: "",
    branch: "",
    semester: "",
  });

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage(null);

    try {
      const response = await fetch("http://localhost:8080/students/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });

      if (!response.ok) {
        throw new Error("Failed to add student");
      }

      await response.json();
      setMessage({ type: "success", text: "Student added successfully!" });

      // reset form
      setForm({
        rollNo: "",
        firstName: "",
        lastName: "",
        branch: "",
        semester: "",
      });
    } catch (err) {
      setMessage({ type: "error", text: err.message });
    } finally {
      setLoading(false);
    }
  };

  return (
    <main className="min-h-screen bg-gradient-to-br from-gray-50 to-white p-6">
      <div className="max-w-2xl mx-auto">
        <div className="bg-white/80 backdrop-blur-sm shadow-xl rounded-2xl p-8">
          <h1 className="text-3xl sm:text-4xl font-extrabold mb-6 text-gray-900">
            Add Student
          </h1>

          {message && (
            <div
              className={`mb-4 p-3 rounded-lg text-sm ${
                message.type === "success"
                  ? "bg-green-100 text-green-800 border border-green-300"
                  : "bg-red-100 text-red-800 border border-red-300"
              }`}
            >
              {message.text}
            </div>
          )}

          <form onSubmit={handleSubmit} className="space-y-6 text-black">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Roll Number
              </label>
              <input
                type="text"
                name="rollNo"
                value={form.rollNo}
                onChange={handleChange}
                placeholder="Enter PRN Number"
                required
                className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-sky-500 focus:border-transparent transition-all"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                First Name
              </label>
              <input
                type="text"
                name="firstName"
                value={form.firstName}
                onChange={handleChange}
                placeholder="Enter First Name"
                required
                className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-sky-500 focus:border-transparent transition-all"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Last Name
              </label>
              <input
                type="text"
                name="lastName"
                value={form.lastName}
                onChange={handleChange}
                placeholder="Enter Last Name"
                required
                className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-sky-500 focus:border-transparent transition-all"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Branch
              </label>
              <select
                name="branch"
                value={form.branch}
                onChange={handleChange}
                required
                className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-sky-500 focus:border-transparent transition-all"
              >
                <option value="">Select Branch</option>
                <option value="COMPUTER_SCIENCE">Computer Science</option>
                <option value="INFORMATION_TECHNOLOGY">
                  Information Technology
                </option>
                <option value="CSE_AI">CSE (AI)</option>
                <option value="ELECTRONICS">Electronics</option>
                <option value="MECHANICAL">Mechanical</option>
                <option value="CIVIL">Civil</option>
              </select>
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Semester
              </label>
              <select
                name="semester"
                value={form.semester}
                onChange={handleChange}
                required
                className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-sky-500 focus:border-transparent transition-all"
              >
                <option value="">Select Semester</option>
                <option value="FIRST">Semester 1</option>
                <option value="SECOND">Semester 2</option>
                <option value="THIRD">Semester 3</option>
                <option value="FOURTH">Semester 4</option>
                <option value="FIFTH">Semester 5</option>
                <option value="SIXTH">Semester 6</option>
                <option value="SEVENTH">Semester 7</option>
                <option value="EIGHTH">Semester 8</option>
              </select>
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full px-6 py-3 bg-gradient-to-r from-blue-500 to-indigo-600 text-white font-medium rounded-xl hover:from-blue-600 hover:to-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-400 transition-all transform hover:-translate-y-0.5 shadow-sm disabled:opacity-70 disabled:cursor-not-allowed"
            >
              {loading ? "Adding..." : "Add Student"}
            </button>
          </form>
        </div>
      </div>
    </main>
  );
}
