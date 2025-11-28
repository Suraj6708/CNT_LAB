import { useEffect, useState } from "react";

export default function StudentList() {
  const [students, setStudents] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/students")
      .then((res) => res.json())
      .then((data) => setStudents(data))
      .catch((err) => console.error(err));
  }, [students]);

  console.log(students);

  return (
    <main className="min-h-screen bg-gradient-to-br from-gray-50 to-white p-6">
      <div className="max-w-6xl mx-auto">
        <div className="bg-white/80 backdrop-blur-sm shadow-xl rounded-2xl p-8">
          <h1 className="text-3xl sm:text-4xl font-extrabold mb-6 text-gray-900">
            Students
          </h1>

          <div className="overflow-x-auto">
            <table className="w-full border-collapse bg-white rounded-xl shadow-lg overflow-hidden">
              <thead>
                <tr className="bg-gradient-to-r from-sky-500 to-indigo-600 text-white">
                  <th className="p-4 text-left font-semibold">PRN Number</th>
                  <th className="p-4 text-left font-semibold">Name</th>
                  <th className="p-4 text-left font-semibold">Branch</th>
                  <th className="p-4 text-left font-semibold">Semester</th>
                </tr>
              </thead>
              <tbody className="text-gray-950">
                {students.map((s, index) => (
                  <tr
                    key={s.student_id}
                    className={`${
                      index % 2 === 0 ? "bg-gray-50" : "bg-white"
                    } hover:bg-gray-100 transition-colors `}
                  >
                    <td className="p-4 border-b border-gray-200 font-medium">
                      {s.rollNo}
                    </td>
                    <td className="p-4 border-b border-gray-200">
                      {s.firstName} {s.lastName}
                    </td>
                    <td className="p-4 border-b border-gray-200">{s.branch}</td>
                    <td className="p-4 border-b border-gray-200">
                      {s.semester}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>

          {students.length === 0 && (
            <div className="text-center py-8 text-gray-500">
              <p className="text-lg">No students found</p>
            </div>
          )}
        </div>
      </div>
    </main>
  );
}
