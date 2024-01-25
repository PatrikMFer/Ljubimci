import IndexPage from "./IndexPage";
import DataTablePage from "./DataTablePage";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<IndexPage />} />
        <Route path="/datatable" element={<DataTablePage />} />
      </Routes>
    </Router>
  );
};

export default App;
