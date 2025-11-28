import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import StudentList from "./Pages/StudentList";
import AddStudent from "./Pages/AddStudent";
import AddMarks from "./Pages/AddMarks";
import ResultPage from "./Pages/ResultPage";
import Navbar from "./Components/Navbar";

export default function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<StudentList />} />
        <Route path="/add-student" element={<AddStudent />} />
        <Route path="/add-marks" element={<AddMarks />} />
        <Route path="/results" element={<ResultPage />} />
      </Routes>
    </Router>
  );
}
