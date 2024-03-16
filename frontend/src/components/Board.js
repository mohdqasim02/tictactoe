import { connect } from "react-redux";
import { MAKE_MOVE } from "../game/actions";

const Board = ({ moves, makeMove }) => {
  const cells = new Array(9).fill("").map((value, index) => {
    const position = index + 1;

    return (
        <div className="cell" key={index} onClick={() => makeMove(position)}>
          <span className={position === moves.error ? "wrong" : ""}>{moves[position] || value}</span>
        </div>
    )
  });

  return <div className="board"> {cells}</div>;
};

const mapStateToProps = state => ({
  moves: state.moves,
});

const mapDispatchToProps = dispatch => ({
  makeMove: position => dispatch({ type: MAKE_MOVE, position }),
});

export default connect(mapStateToProps, mapDispatchToProps)(Board);
