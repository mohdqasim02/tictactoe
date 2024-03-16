import { useState } from "react";
import { connect } from "react-redux";
import Modal from "react-modal";

const customStyles = {
  content: {
    top: "20%",
    left: "50%",
    right: "auto",
    bottom: "auto",
    marginRight: "-50%",
    transform: "translate(-50%, -50%)",
  },
};

Modal.setAppElement("#root");

export const Result = ({ isGameOver, winner }) => {
  const [modalIsOpen, setIsOpen] = useState(isGameOver);
  const resultText = winner ? `Winner : ${winner}` : "Game is drawn";

  const closeModal = () => {
    setIsOpen(false);
    window.location.href = "/";
  };

  return (
    <Modal
      isOpen={modalIsOpen}
      onRequestClose={closeModal}
      style={customStyles}
    >
      <h3>{resultText}</h3>
      <button onClick={closeModal}> Play again </button>
    </Modal>
  );
};

const mapStateToProps = state => ({
  winner: state.winner,
  isGameOver: state.isGameOver,
});

const mapDispatchToProps = dispatch => ({});

export default connect(mapStateToProps, mapDispatchToProps)(Result);
