import { NavLink } from "react-router-dom";

export default function Navbar() {
  const base =
    "px-4 py-2 rounded-md font-medium focus:outline-none focus:ring-2 focus:ring-offset-2";
  const inactive = "text-gray-700 bg-white hover:bg-gray-100";
  const active = "text-white bg-blue-600 hover:bg-blue-700";

  const linkClass = ({ isActive }) =>
    `${base} ${isActive ? active : inactive} transition-colors duration-150`;

  return (
    <nav className="bg-gray-50 border-b border-gray-200">
      <div className="max-w-6xl mx-auto px-4">
        <div className="flex items-center justify-between h-16">
          <div className="flex items-center gap-4">
            <div className="text-xl font-semibold text-gray-800">
              Student App
            </div>
            <NavLink to="/" className={linkClass}>
              Search List
            </NavLink>
            <div className="flex items-center space-x-2">
              <NavLink to="/add-student" className={linkClass}>
                Add Student
              </NavLink>
              <NavLink to="/add-marks" className={linkClass}>
                Add Marks
              </NavLink>
              <NavLink to="/results" className={linkClass}>
                Search Result
              </NavLink>
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
}
