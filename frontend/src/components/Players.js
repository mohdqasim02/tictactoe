import React from "react";
import { connect } from "react-redux";

const Player = ({ name, symbol, active }) => (
  <h3 className={active ? "current-player" : ""}>{`${name} : ${symbol}`}</h3>
);

const Players = ({ players: [player1, player2], currentPlayer }) => {
  const isP1Active = player1.symbol === currentPlayer.symbol;
  const isP2Active = !isP1Active;

  return (
    <div className="players">
      <Player name={player1.name} symbol={player1.symbol} active={isP1Active} />
      <Player name={player2.name} symbol={player2.symbol} active={isP2Active} />
    </div>
  );
};

const mapStateToProps = state => ({
  players: state.players,
  currentPlayer: state.currentPlayer,
});

export default connect(mapStateToProps)(Players);
