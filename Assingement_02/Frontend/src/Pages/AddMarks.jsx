import { useState } from "react";

export default function AddMarks() {
  const [form, setForm] = useState({
    rollNo: "",
    subjectCode: "",
    mseMarks: "",
    eseMarks: "",
  });
  const [status, setStatus] = useState(null);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // basic validation
    const rollNo = String(form.rollNo).trim();
    const subjectCode = String(form.subjectCode).trim();
    const mse = Number(form.mseMarks);
    const ese = Number(form.eseMarks);

    if (!rollNo || !subjectCode) {
      setStatus({
        type: "error",
        message: "Roll No and Subject Code are required.",
      });
      return;
    }
    if (Number.isNaN(mse) || Number.isNaN(ese)) {
      setStatus({ type: "error", message: "Marks must be valid numbers." });
      return;
    }
    if (mse < 0 || mse > 30 || ese < 0 || ese > 70) {
      setStatus({ type: "error", message: "Marks out of allowed range." });
      return;
    }

    const payload = {
      rollNo,
      subjectCode,
      mseMarks: mse,
      eseMarks: ese,
    };

    setStatus({ type: "pending", message: "Sending..." });

    console.log(payload);

    fetch("http://localhost:8080/results/add-marks", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    })
      .then((res) => {
        if (!res.ok) throw new Error("Failed to add marks");
        return res.json();
      })
      .then(() =>
        setStatus({ type: "success", message: "Marks added successfully." })
      )
      .catch((err) =>
        setStatus({ type: "error", message: err.message || "Request failed" })
      );
  };

  return (
    <main className="text-black min-h-screen bg-gradient-to-br from-gray-50 to-white p-6">
      <div className="max-w-2xl mx-auto">
        <div className="bg-white/80 backdrop-blur-sm shadow-xl rounded-2xl p-8">
          <h1 className="text-3xl sm:text-4xl font-extrabold mb-6 text-gray-900">
            Add Marks
          </h1>

          <form onSubmit={handleSubmit} className="space-y-6">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Roll No
              </label>
              <input
                className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-sky-500 focus:border-transparent transition-all"
                name="rollNo"
                placeholder="Enter Roll No (e.g. 21BCE1234)"
                onChange={handleChange}
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Subject Code
              </label>
              <input
                className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-sky-500 focus:border-transparent transition-all"
                name="subjectCode"
                placeholder="Enter Subject Code (e.g. CS501)"
                onChange={handleChange}
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                MSE Marks (0-30)
              </label>
              <input
                className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-sky-500 focus:border-transparent transition-all"
                name="mseMarks"
                type="number"
                min="0"
                max="30"
                placeholder="Enter MSE marks"
                onChange={handleChange}
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                ESE Marks (0-70)
              </label>
              <input
                className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-sky-500 focus:border-transparent transition-all"
                name="eseMarks"
                type="number"
                min="0"
                max="70"
                placeholder="Enter ESE marks"
                onChange={handleChange}
                required
              />
            </div>

            {status && (
              <div
                className={`px-4 py-2 rounded-md text-sm ${
                  status.type === "error"
                    ? "bg-red-100 text-red-800"
                    : status.type === "success"
                    ? "bg-green-100 text-green-800"
                    : "bg-yellow-100 text-yellow-800"
                }`}
              >
                {status.message}
              </div>
            )}

            <button
              type="submit"
              className="w-full px-6 py-3 bg-gradient-to-r from-blue-500 to-blue-600 text-white font-medium rounded-xl hover:from-blue-600 hover:to-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-400 transition-all transform hover:-translate-y-0.5 shadow-sm"
            >
              Add Marks
            </button>
          </form>
        </div>
      </div>
    </main>
  );
}
