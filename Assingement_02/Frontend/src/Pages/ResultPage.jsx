import { useState } from "react";

export default function ResultPage() {
  const [studentId, setStudentId] = useState("");
  const [results, setResults] = useState([]);

  const fetchResults = () => {
    fetch(`http://localhost:8080/results/student/${studentId}/details`)
      .then((res) => res.json())
      .then((data) => setResults(data))
      .catch((err) => console.error(err));
  };

  console.log(results);

  return (
    <main className="min-h-screen bg-gradient-to-br from-gray-50 to-white p-6">
      <div className="max-w-6xl mx-auto">
        <div className="bg-white/80 backdrop-blur-sm shadow-xl rounded-2xl p-8">
          <h1 className="text-3xl sm:text-4xl font-extrabold mb-6 text-gray-900">
            Student Result
          </h1>

          <div className="flex flex-col sm:flex-row gap-4 mb-6">
            <input
              className=" text-black flex-1 px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-sky-500 focus:border-transparent transition-all"
              placeholder="Enter Student ID"
              value={studentId}
              onChange={(e) => setStudentId(e.target.value)}
            />
            <button
              onClick={fetchResults}
              className="px-6 py-3 bg-gradient-to-r from-sky-500 to-indigo-600 text-white font-medium rounded-xl hover:from-sky-600 hover:to-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-400 transition-all transform hover:-translate-y-0.5 shadow-sm"
            >
              Get Result
            </button>
          </div>

          {results.length > 0 && (
            <div className="overflow-x-auto">
              <table className="w-full border-collapse text-black bg-white rounded-xl shadow-lg overflow-hidden">
                <thead>
                  <tr className="bg-gradient-to-r from-sky-500 to-indigo-600 text-white">
                    <th className="p-4 text-left font-semibold">Subject</th>
                    <th className="p-4 text-left font-semibold">MSE</th>
                    <th className="p-4 text-left font-semibold">ESE</th>
                    <th className="p-4 text-left font-semibold">Total</th>
                    <th className="p-4 text-left font-semibold">Grade</th>
                  </tr>
                </thead>
                <tbody>
                  {results.map((r, index) => (
                    <tr
                      key={r.result_id}
                      className={`${
                        index % 2 === 0 ? "bg-gray-50" : "bg-white"
                      } hover:bg-gray-100 transition-colors`}
                    >
                      <td className="p-4 border-b border-gray-200 font-medium">
                        {r.subjectName}
                      </td>
                      <td className="p-4 border-b border-gray-200">
                        {r.mseMarks}
                      </td>
                      <td className="p-4 border-b border-gray-200">
                        {r.eseMarks}
                      </td>
                      <td className="p-4 border-b border-gray-200 font-semibold">
                        {r.totalMarks}
                      </td>
                      <td className="p-4 border-b border-gray-200">
                        <span
                          className={`px-3 py-1 rounded-full text-sm font-medium ${
                            r.grade === "A"
                              ? "bg-green-100 text-green-800"
                              : r.grade === "B"
                              ? "bg-blue-100 text-blue-800"
                              : r.grade === "C"
                              ? "bg-yellow-100 text-yellow-800"
                              : "bg-red-100 text-red-800"
                          }`}
                        >
                          {r.grade}
                        </span>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}

          {results.length === 0 && studentId && (
            <div className="text-center py-8 text-gray-500">
              <p className="text-lg">No results found for this student</p>
            </div>
          )}
        </div>
      </div>
    </main>
  );
}
