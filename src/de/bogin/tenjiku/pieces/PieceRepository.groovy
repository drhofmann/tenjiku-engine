package de.bogin.tenjiku.pieces

import de.bogin.tenjiku.moves.MovementRepository

class PieceRepository {
	enum EvaluationScheme {
		CHU_SHOGI, CHESS
	}
	
	MovementRepository movementRepository = new MovementRepository()
	EvaluationScheme evaluationScheme
	private Map<EvaluationScheme, Map<PieceType, Double>> values
	
	Piece create (PieceType pieceType, PlayerColour colour){
		Piece piece
		switch(pieceType)
		{
			case PieceType.PAWN:
			piece = new Pawn()
			break
			
			case PieceType.PAWN_CHESS:
			piece = new Pawn_Chess()
			break
			
			case PieceType.DOG:
			piece = new Dog()
			break
			
			case PieceType.GOBETWEEN:
			piece = new GoBetween()
			break
			
			case PieceType.COPPER_GENERAL:
			piece = new CopperGeneral()
			break
			
			case PieceType.SILVER_GENERAL:
			piece = new SilverGeneral()
			break
			
			case PieceType.DRUNK_ELEPHANT:
			piece = new DrunkElephant()
			break
			
			case PieceType.BLIND_TIGER:
			piece = new BlindTiger()
			break
			
			case PieceType.FEROCIOUS_LEOPARD:
			piece = new FerociousLeopard()
			break
			
			case PieceType.GOLD_GENERAL:
			piece = new GoldGeneral()
			break
			
			case PieceType.KING:
			piece = new King()
			break
			
			case PieceType.ROOK:
			piece = new Rook()
			break
			
			case PieceType.DRAGON_HORSE:
			piece = new DragonHorse()
			break
			
			case PieceType.DRAGON_KING:
			piece = new DragonKing()
			break
			
			case PieceType.SIDE_MOVER:
			piece = new SideMover()
			break
			
			case PieceType.VERTICAL_MOVER:
			piece = new VerticalMover()
			break
			
			case PieceType.BISHOP:
			piece = new Bishop()
			break
			
			case PieceType.FREE_KING:
			piece = new FreeKing()
			break
			
			case PieceType.KNIGHT_CHESS:
			piece = new Knight_Chess()
			break
			
			case PieceType.KNIGHT:
			piece = new Knight()
			break
			
			case PieceType.LANCE:
			piece = new Lance()
			break
			
			case PieceType.REVERSE_CHARIOT:
			piece = new ReverseChariot()
			break
			
			case PieceType.KIRIN:
			piece = new Kirin()
			break
			
			case PieceType.PHOENIX:
			piece = new Phoenix()
			break
			
			case PieceType.LION_CHU:
			piece = new Lion_ChuShogi()
			break
			
			case PieceType.LION_TENJIKU:
			piece = new Lion_TenjikuShogi()
			break
			
			default:
			throw new RuntimeException("Unknown piece type $pieceType")
		}
		piece.owner = colour
		piece.type = pieceType
		piece.movementRepository=movementRepository
		Map scheme = values[evaluationScheme]
		piece.value = scheme[piece.type.name()]
		return piece
	}
	
	PieceRepository(){
		values = [:]
		Map chessValues = [PAWN_CHESS:1, KNIGHT_CHESS:3, BISHOP:3, ROOK:5, FREE_KING:9, KING:100]
		Map chuShogiValues = [PAWN:1, GOBETWEEN:1, COPPER_GENERAL:2, SILVER_GENERAL:2, BLIND_TIGER:3,
			 DRUNK_ELEPHANT:3, FEROCIOUS_LEOPARD:3, GOLD_GENERAL:3, BISHOP:5, ROOK:6, FREE_KING: 12,
			 DRAGON_HORSE:7, DRAGON_KING:8, SIDE_MOVER:4, VERTICAL_MOVER:4, LANCE:3, REVERSE_CHARIOT:3,
			 PHOENIX:3, KIRIN:3, LION_CHU:20, LION_TENJIKU:20,
			 KING: 400]
		values[EvaluationScheme.CHESS]=chessValues
		values[EvaluationScheme.CHU_SHOGI]=chuShogiValues
	}
}
