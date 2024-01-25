import IndexPage from "./IndexPage";
import DataTablePage from "./DataTablePage";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import ProfilePage from "./ProfilePage";

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<IndexPage />} />
        <Route path="/datatable" element={<DataTablePage />} />
        <Route path="/profile" element={<ProfilePage />} />{" "}
      </Routes>
    </Router>
  );
};

export default App;
