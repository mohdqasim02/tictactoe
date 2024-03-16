class GameController {
  static highLightError({message, position}) {
    const mainContainer = document.querySelector("#main-container");
    const error = document.createElement("div");

    error.innerText = message;
    error.classList.add("error");
    mainContainer.append(error);
    setTimeout(() => {
      mainContainer.removeChild(mainContainer.lastChild);
    }, 1000);
  }

  static registerPlayer = player => {
    const init = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(player),
    };

    return fetch("/lobby/add", init)
      .then(res => res.json())
      .then(_ => (window.location.href = "/lobby"));
  };

  static fetchGameStatus = () => fetch("/game/status").then(res => res.json());

  static makeMove = position => {
    const init = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ position }),
    };

    return fetch("/game/move", init).then(async res => {
      if (!res.ok) {
        const result = await res.json()
        GameController.highLightError(result);
      }
    });
  };
}

export default GameController;
