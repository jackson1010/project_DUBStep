import { Route, Routes } from "react-router-dom";
import { useState } from "react";
import HomePage from "./pages/HomePage/HomePage";
import AuthPage from "./pages/AuthPage/AuthPage";
import PageLayout from "./layouts/PageLayout/PageLayout";
import ReportPage from "./pages/ReportPage/ReportPage";
import ProfilePage from "./pages/ProfilePage/ProfilePage";
import StatPage from "./pages/StatPage/StatPage";

function App() {
	const [isLoggedIn, setIsLoggedIn] = useState(false);
  console.log('is logged in?? ', isLoggedIn);
  return (
    <PageLayout isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn}>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/auth" element={<AuthPage isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />} />
        <Route path="/report" element={<ReportPage />} />
        <Route path="/stat" element={<StatPage />} />
        <Route path="/profile" element={<ProfilePage />} />
      </Routes>
    </PageLayout>
  );
}

export default App;
