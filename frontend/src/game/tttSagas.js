import { all, call, put, takeLatest } from "redux-saga/effects";
import GameController from "./GameController";
import {
  FETCH_GAME_STATUS, INVALID_MOVE,
  MAKE_MOVE,
  REGISTER_PLAYER_REQUESTED,
  UPDATE_GAME_STATUS,
} from "./actions";

function* registerPlayer({ player }) {
  yield call(GameController.registerPlayer, player);
}

function* watchNewPlayer() {
  yield takeLatest(REGISTER_PLAYER_REQUESTED, registerPlayer);
}

function* fetchGameStatus() {
  const gameState = yield call(GameController.fetchGameStatus);
  yield put({ type: UPDATE_GAME_STATUS, ...gameState });
}

function* watchGameStatus() {
  yield takeLatest(FETCH_GAME_STATUS, fetchGameStatus);
}

function* makeMove({ position }) {
    yield call(GameController.makeMove, position);
}

function* watchMoves() {
  yield takeLatest(MAKE_MOVE, makeMove);
}

export default function* rootSaga() {
  yield all([watchNewPlayer(), watchGameStatus(), watchMoves()]);
}
