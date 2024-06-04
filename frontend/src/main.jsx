import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import AddDeviceToRoomPage from './pages/AddDeviceToRoomPage.jsx';
import MainPage from "./pages/MainPage";
import DevicesInRoomPage from "./pages/DevicesInRoomPage";
import DevicePage from "./pages/DevicePage.jsx";
import "./index.css";
import AppProvider from "./context/AppProvider.jsx";
import LogsPage from "./pages/LogsPage.jsx";
import LogsResultsPage from "./pages/LogsResultsPage.jsx";

ReactDOM.createRoot(document.getElementById('root')).render(
    <AppProvider>
        <React.StrictMode>
            <Router>
                <Routes>
                    <Route path="/" element={<MainPage />} />
                    <Route path="/rooms/:roomId" element={<AddDeviceToRoomPage/>}/>
                    <Route path="/rooms/:roomId/devices" element={<DevicesInRoomPage/>}/>
                    <Route path="/devices/:deviceId" element={<DevicePage/>}/>
                    <Route path="/logs/:deviceId" element={<LogsPage />} />
                    <Route path="/logs/:deviceId/results" element={<LogsResultsPage />} />
                </Routes>
            </Router>
        </React.StrictMode>
    </AppProvider>

);
