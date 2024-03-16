import React from "react";
import "./index.css";
import ReactDOM from "react-dom/client";
import App from "./App.js";
import { Provider } from "react-redux";
import ticTacToeReducer from "./game/tttReducer.js";
import { configureStore } from "@reduxjs/toolkit";
import createSagaMiddleware from "redux-saga";
import rootSaga from "./game/tttSagas.js";

const sagaMiddleware = createSagaMiddleware();
const store = configureStore({
  reducer: ticTacToeReducer,
  middleware: getDefaultMiddleware =>
    getDefaultMiddleware().concat(sagaMiddleware),
});

sagaMiddleware.run(rootSaga);

ReactDOM.createRoot(document.getElementById("root")).render(
  <Provider store={store}>
    <App />
  </Provider>
);
