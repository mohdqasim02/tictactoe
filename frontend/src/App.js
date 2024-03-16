import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import "./style.css";
import SignUp from "./components/SignUp";
import { Lobby } from "./components/Lobby";
import Game from "./components/Game";

const App = () => (
  <div id="main-container">
    <h2>Tic Tac Toe</h2>
    <Router>
      <Routes>
        <Route path="/" exact element={<SignUp />} />
        <Route path="/lobby" exact element={<Lobby />} />
        <Route path="/game" exact element={<Game />} />
      </Routes>
    </Router>
  </div>
);

export default App;
