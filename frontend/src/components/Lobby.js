import { useEffect } from "react";

export const Lobby = () => {
  const startGame = () => {
    fetch("/lobby/start", {
      method: "POST",
    }).then(_ => (window.location.href = "/game"));
  };

  useEffect(() => {
    const intervalId = setInterval(() => {
      fetch("/lobby/status")
        .then(res => res.json())
        .then(lobbyStatus => {
          if (lobbyStatus === "READY" || lobbyStatus === "EXPIRED") startGame();
        });
    }, 1000);

    return () => clearInterval(intervalId);
  });

  return <div>Waiting...</div>;
};
