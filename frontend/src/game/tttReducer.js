import {UPDATE_GAME_STATUS} from "./actions";

const initialState = {
  players: [
    { name: "P1", symbol: "X" },
    { name: "P2", symbol: "O" },
  ],
  moves: {},
  currentPlayer: {},
  nextPlayer: {},
  isGameOver: false,
  winner: null,
};

const updateGameState = newState => ({
    players: newState.players,
    moves: newState.moves,
    currentPlayer: newState.currentPlayer,
    nextPlayer: newState.nextPlayer,
    isGameOver: newState.isGameOver,
    winner: newState.winner,
});

const ticTacToeReducer = (state = initialState, action) => {
    switch (action.type) {
        case UPDATE_GAME_STATUS:
            return updateGameState(action);
        default:
            return state;
    }
};

export default ticTacToeReducer;
