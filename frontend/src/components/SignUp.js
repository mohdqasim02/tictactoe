import { connect } from "react-redux";
import { REGISTER_PLAYER_REQUESTED } from "../game/actions";

const SignUp = ({ registerPlayer }) => {
  const getPlayerName = event => {
    event.preventDefault();
    const formData = new FormData(event.target);

    registerPlayer({ name: formData.get("player-name") });
  };

  return (
    <form onSubmit={getPlayerName}>
      <input type="text" name="player-name" placeholder="Player name" />
    </form>
  );
};

const mapStateToProps = state => ({});
const mapDispatchToProps = dispatch => ({
  registerPlayer: player =>
    dispatch({ type: REGISTER_PLAYER_REQUESTED, player }),
});

export default connect(mapStateToProps, mapDispatchToProps)(SignUp);
