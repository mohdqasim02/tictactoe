import { connect } from "react-redux";
import { useEffect } from "react";
import Board from "./Board";
import Players from "./Players";
import Result from "./Result";
import { FETCH_GAME_STATUS } from "../game/actions";

const Game = ({ isGameOver, getGameStatus }) => {
  useEffect(() => {
    const intervalId = setInterval(getGameStatus, 1000);
    return () => clearInterval(intervalId);
  });

  return (
    <div>
      <Players />
      <Board />
      {isGameOver && <Result />}
    </div>
  );
};

const mapStateToProps = state => ({ isGameOver: state.isGameOver });
const mapDispatchToProps = dispatch => ({
  getGameStatus: () => dispatch({ type: FETCH_GAME_STATUS }),
});

export default connect(mapStateToProps, mapDispatchToProps)(Game);
