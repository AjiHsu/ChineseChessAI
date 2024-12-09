import copy

pieceEvaluate = [0, 250, 250, 500, 300, 300, 80]
mobilityEvaluate = [0, 1, 1, 6, 12, 6, 15, 0]

kingPositionEvaluate = [
    [
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, -9, -9, -9, 0, 0, 0],
        [0, 0, 0, -8, -8, -8, 0, 0, 0],
        [0, 0, 0, 1, 5, 1, 0, 0, 0]
    ],
    [
        [0, 0, 0, 1, 5, 1, 0, 0, 0],
        [0, 0, 0, -8, -8, -8, 0, 0, 0],
        [0, 0, 0, -9, -9, -9, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0]
    ]
]

advisorPositionEvaluate = [
    [
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 3, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 3, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0]
    ]
]

elephantPositionEvaluate = [
    [
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [-2, 0, 0, 0, 3, 0, 0, 0, -2],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [-2, 0, 0, 0, 3, 0, 0, 0, -2],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0]
    ]
]

chariotPositionEvaluate = [
    [
        [6, 8, 7, 13, 14, 13, 7, 8, 6],
        [6, 12, 9, 16, 33, 16, 9, 12, 6],
        [6, 8, 7, 14, 16, 14, 7, 8, 6],
        [6, 13, 13, 16, 16, 16, 13, 13, 6],
        [8, 11, 11, 14, 15, 14, 11, 11, 8],
        [8, 12, 12, 14, 15, 14, 12, 12, 8],
        [4, 9, 4, 12, 14, 12, 4, 9, 4],
        [-2, 8, 4, 12, 12, 12, 4, 8, -2],
        [5, 8, 6, 12, 0, 12, 6, 8, 5],
        [-6, 6, 4, 12, 0, 12, 4, 6, -6]
    ],
    [
        [-6, 6, 4, 12, 0, 12, 4, 6, -6],
        [5, 8, 6, 12, 0, 12, 6, 8, 5],
        [-2, 8, 4, 12, 12, 12, 4, 8, -2],
        [4, 9, 4, 12, 14, 12, 4, 9, 4],
        [8, 12, 12, 14, 15, 14, 12, 12, 8],
        [8, 11, 11, 14, 15, 14, 11, 11, 8],
        [6, 13, 13, 16, 16, 16, 13, 13, 6],
        [6, 8, 7, 14, 16, 14, 7, 8, 6],
        [6, 12, 9, 16, 33, 16, 9, 12, 6],
        [6, 8, 7, 13, 14, 13, 7, 8, 6]
    ]
]

horsePositionEvaluate = [
    [
        [2, 2, 2, 8, 2, 8, 2, 2, 2],
        [2, 8, 15, 9, 6, 9, 15, 8, 2],
        [4, 10, 11, 15, 11, 15, 11, 10, 4],
        [5, 20, 12, 19, 12, 19, 12, 20, 5],
        [2, 12, 11, 15, 16, 15, 11, 12, 2],
        [2, 10, 13, 14, 15, 14, 13, 10, 2],
        [4, 6, 10, 7, 10, 7, 10, 6, 4],
        [5, 4, 6, 7, 4, 7, 6, 4, 5],
        [-3, 2, 4, 5, -10, 5, 4, 2, -3],
        [0, -3, 2, 0, 2, 0, 2, -3, 0]
    ],
    [
        [0, -3, 2, 0, 2, 0, 2, -3, 0],
        [-3, 2, 4, 5, -10, 5, 4, 2, -3],
        [5, 4, 6, 7, 4, 7, 6, 4, 5],
        [4, 6, 10, 7, 10, 7, 10, 6, 4],
        [2, 10, 13, 14, 15, 14, 13, 10, 2],
        [2, 12, 11, 15, 16, 15, 11, 12, 2],
        [5, 20, 12, 19, 12, 19, 12, 20, 5],
        [4, 10, 11, 15, 11, 15, 11, 10, 4],
        [2, 8, 15, 9, 6, 9, 15, 8, 2],
        [2, 2, 2, 8, 2, 8, 2, 2, 2]
    ]
]

cannonPositionEvaluate = [
    [
        [4, 4, 0, -5, -6, -5, 0, 4, 4],
        [2, 2, 0, -4, -7, -4, 0, 2, 2],
        [1, 1, 0, -5, -4, -5, 0, 1, 1],
        [0, 3, 3, 2, 4, 2, 3, 3, 0],
        [0, 0, 0, 0, 4, 0, 0, 0, 0],
        [-1, 0, 3, 0, 4, 0, 3, 0, -1],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [1, 0, 4, 3, 5, 3, 4, 0, 1],
        [0, 1, 2, 2, 2, 2, 2, 1, 0],
        [0, 0, 1, 3, 3, 3, 1, 0, 0]
    ],
    [
        [0, 0, 1, 3, 3, 3, 1, 0, 0],
        [0, 1, 2, 2, 2, 2, 2, 1, 0],
        [1, 0, 4, 3, 5, 3, 4, 0, 1],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [-1, 0, 3, 0, 4, 0, 3, 0, -1],
        [0, 0, 0, 0, 4, 0, 0, 0, 0],
        [0, 3, 3, 2, 4, 2, 3, 3, 0],
        [1, 1, 0, -5, -4, -5, 0, 1, 1],
        [2, 2, 0, -4, -7, -4, 0, 2, 2],
        [4, 4, 0, -5, -6, -5, 0, 4, 4]
    ]
]

soldierPositionEvaluate = [
    [
        [0, 0, 0, 2, 4, 2, 0, 0, 0],
        [20, 30, 50, 65, 70, 65, 50, 30, 20],
        [20, 30, 45, 55, 55, 55, 45, 30, 20],
        [20, 27, 30, 40, 42, 40, 30, 27, 20],
        [10, 18, 22, 35, 40, 35, 22, 18, 10],
        [3, 0, 4, 0, 7, 0, 4, 0, 3],
        [-2, 0, -2, 0, 6, 0, -2, 0, -2],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0]
    ],
    [
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [-2, 0, -2, 0, 6, 0, -2, 0, -2],
        [3, 0, 4, 0, 7, 0, 4, 0, 3],
        [10, 18, 22, 35, 40, 35, 22, 18, 10],
        [20, 27, 30, 40, 42, 40, 30, 27, 20],
        [20, 30, 45, 55, 55, 55, 45, 30, 20],
        [20, 30, 50, 65, 70, 65, 50, 30, 20],
        [0, 0, 0, 2, 4, 2, 0, 0, 0]
    ]
]

positionEvaluate = [ kingPositionEvaluate, advisorPositionEvaluate, elephantPositionEvaluate, chariotPositionEvaluate, horsePositionEvaluate, cannonPositionEvaluate, soldierPositionEvaluate ]

def findKingLocation(boardPiece, boardGroup):
    kingLocation = [-1, -1, -1, -1] # ri, rj, bi, bj
    for i in range(10):
        for j in range(9):
            if boardPiece[i][j] == 0:
                if boardGroup[i][j] == 0:
                    kingLocation[0] = i
                    kingLocation[1] = j
                elif boardGroup[i][j] == 1:
                    kingLocation[2] = i
                    kingLocation[3] = j
    return kingLocation

def inBound(i, j):
    return 0 <= i < 10 and 0 <= j < 9

def inBound3x3(i, j, group):
    if group == 0:  # RED
        return 7 <= i <= 9 and 3 <= j <= 5
    elif group == 1:  # BLACK
        return 0 <= i <= 2 and 3 <= j <= 5
    else:
        return False

def inBoundHalf(i, j, group):
    if group == 0:  # RED
        return 5 <= i <= 9 and 0 <= j <= 8
    elif group == 1:  # BLACK
        return 0 <= i <= 4 and 0 <= j <= 8
    else:
        return False

def kingToKing(boardPiece, boardGroup, i1, j1, i2, j2): # should copy
    ep1 = boardPiece[i1][j1]
    eb1 = boardGroup[i1][j1]
    ep2 = boardPiece[i2][j2]
    eb2 = boardGroup[i2][j2]

    boardPiece[i2][j2] = ep1
    boardGroup[i2][j2] = eb1
    boardPiece[i1][j1] = 7  # EMPTY
    boardGroup[i1][j1] = 2  # EMPTY

    kingLocation = findKingLocation(boardPiece, boardGroup)
    if kingLocation[1] != kingLocation[3]:
        # retrieve
        boardPiece[i1][j1] = ep1
        boardGroup[i1][j1] = eb1
        boardPiece[i2][j2] = ep2
        boardGroup[i2][j2] = eb2
        return False
    else:
        for i in range(kingLocation[2] + 1, kingLocation[0]):
            if boardPiece[i][kingLocation[1]] != 7:
                # retrieve
                boardPiece[i1][j1] = ep1
                boardGroup[i1][j1] = eb1
                boardPiece[i2][j2] = ep2
                boardGroup[i2][j2] = eb2
                return False
    # retrieve
    boardPiece[i1][j1] = ep1
    boardGroup[i1][j1] = eb1
    boardPiece[i2][j2] = ep2
    boardGroup[i2][j2] = eb2
    return True


def generateSingleMoves(boardPiece, boardGroup, i, j, turn): # return [[i, j]...]
    piece = boardPiece[i][j]
    moves = []
    dirI = [0, 0, 1, -1]
    dirJ = [1, -1, 0, 0]

    if piece == 0:  # KING
        for k in range(4):
            ii = i + [0, 0, 1, -1][k]
            jj = j + [1, -1, 0, 0][k]
            if inBound3x3(ii, jj, turn):
                moves.append([ii, jj])
    elif piece == 1:  # ADVISOR
        for k in range(4):
            ii = i + [1, 1, -1, -1][k]
            jj = j + [1, -1, 1, -1][k]
            if inBound3x3(ii, jj, turn):
                moves.append([ii, jj])
    elif piece == 2:  # ELEPHANT
        for k in range(4):
            ii = i + [2, 2, -2, -2][k]
            jj = j + [2, -2, 2, -2][k]
            if inBoundHalf(ii, jj, turn) and boardPiece[i + [2, 2, -2, -2][k] // 2][j + [2, -2, 2, -2][k] // 2] == 7:
                moves.append([ii, jj])
    elif piece == 3:  # CHARIOT
        for k in range(4):
            ti, tj = i, j
            while True:
                ti += dirI[k]
                tj += dirJ[k]
                if not inBound(ti, tj) or boardPiece[ti][tj] != 7:
                    break
                moves.append([ti, tj])
            if inBound(ti, tj):
                moves.append([ti, tj])
    elif piece == 4:  # HORSE
        for k in range(4):
            ii = i + [1, 1, -1, -1, 2, 2, -2, -2][k]
            jj = j + [2, -2, 2, -2, 1, -1, 1, -1][k]
            if inBound(ii, jj) and boardPiece[i][j + [2, -2, 2, -2, 1, -1, 1, -1][k] // 2] == 7:
                moves.append([ii, jj])
        for k in range(4, 8):
            ii = i + [1, 1, -1, -1, 2, 2, -2, -2][k]
            jj = j + [2, -2, 2, -2, 1, -1, 1, -1][k]
            if inBound(ii, jj) and boardPiece[i + [1, 1, -1, -1, 2, 2, -2, -2][k] // 2][j] == 7:
                moves.append([ii, jj])
    elif piece == 5:  # CANNON
        for k in range(4):
            ti, tj = i, j
            while True:
                ti += dirI[k]
                tj += dirJ[k]
                if not inBound(ti, tj) or boardPiece[ti][tj] != 7:
                    break
                moves.append([ti, tj])
            if inBound(ti, tj):
                while True:
                    ti += dirI[k]
                    tj += dirJ[k]
                    if not inBound(ti, tj) or boardPiece[ti][tj] != 7:
                        break   
                if inBound(ti, tj):
                    moves.append([ti, tj])
    elif piece == 6:  # SOLDIER
        if turn == 0:  # RED
            if i >= 5:
                moves.append([i - 1, j])
            else:
                if inBound(i - 1, j):
                    moves.append([i - 1, j])
                if inBound(i, j + 1):
                    moves.append([i, j + 1])
                if inBound(i, j - 1):
                    moves.append([i, j - 1])
        elif turn == 1:  # BLACK
            if i <= 4:
                moves.append([i + 1, j])
            else:
                if inBound(i + 1, j):
                    moves.append([i + 1, j])
                if inBound(i, j + 1):
                    moves.append([i, j + 1])
                if inBound(i, j - 1):
                    moves.append([i, j - 1])
        else:
            print("Wrong turn at generateSingleMoves")
            exit(1)

    moves_ktk = []
    for (ii, jj) in moves:
        if not kingToKing(copy.deepcopy(boardPiece), copy.deepcopy(boardGroup), i, j, ii, jj):
            moves_ktk.append((ii, jj))
    return moves_ktk

def dKing(boardPiece, boardGroup, turn, i1, j1, i2, j2): # should copy
    ep1 = boardPiece[i1][j1]
    eb1 = boardGroup[i1][j1]
    ep2 = boardPiece[i2][j2]
    eb2 = boardGroup[i2][j2]

    boardPiece[i2][j2] = ep1
    boardGroup[i2][j2] = eb1
    boardPiece[i1][j1] = 7 # EMPTY
    boardGroup[i1][j1] = 2 # EMPTY

    kingLocation = findKingLocation(boardPiece, boardGroup)
    kl = []
    if turn == 0:
        kl = [kingLocation[0], kingLocation[1]]
    elif turn == 1:
        kl = [kingLocation[2], kingLocation[3]]
    else:
        print("Wrong turn at dKing")
        exit(1)

    for i in range(10):
        for j in range(9):
            if boardGroup[i][j] != turn and boardGroup[i][j] != 2:
                moves = generateSingleMoves(boardPiece, boardGroup, i, j, (turn + 1) % 2)
                for (i, j) in moves:
                    if i == kl[0] and j == kl[1]:

                        # retrieve
                        boardPiece[i1][j1] = ep1
                        boardGroup[i1][j1] = eb1
                        boardPiece[i2][j2] = ep2
                        boardGroup[i2][j2] = eb2
                        return True
    # retrieve
    boardPiece[i1][j1] = ep1
    boardGroup[i1][j1] = eb1
    boardPiece[i2][j2] = ep2
    boardGroup[i2][j2] = eb2
    return False

def generateMove(boardPiece, boardGroup, turn): # (i1, j1, i2, j2)
    moves = []
    for i in range(10):
        for j in range(9):
            if boardGroup[i][j] == turn:
                move = generateSingleMoves(boardPiece, boardGroup, i, j, turn)
                for (ii, jj) in move:
                    if not dKing(boardPiece, boardGroup, turn, i, j, ii, jj):
                        moves.append([i, j, ii, jj])
    return moves

def generateValidMove(boardPiece, boardGroup, turn):
    resMov = []
    moves = generateMove(boardPiece, boardGroup, turn)
    for (i, j, ii, jj) in moves:
        if boardGroup[i][j] == turn and boardGroup[ii][jj] != turn:
            resMov.append([i, j, ii, jj])
    return resMov

def evaluate(boardPiece, boardGroup, turn):
    print("____start evaluate____")
    maxValue = 10000
    attackWeight = 0.5
    protectWeight = 0.2

    # find opposite group----------
    if turn == 0:  # RED
        oppositeTurn = 1  # BLACK
    elif turn == 1:  # BLACK
        oppositeTurn = 0  # RED
    else:
        print("Wrong turn at evaluate")
        exit(1)
    # end find opposite group----------

    # position of king----------
    king = [-1, -1]
    oppositeKing = [-1, -1]
    for i in range(len(boardPiece)):
        for j in range(len(boardPiece[0])):
            if boardGroup[i][j] == turn:
                if boardPiece[i][j] == 0:  # KING
                    king = [i, j]
            elif boardGroup[i][j] == oppositeTurn:
                if boardPiece[i][j] == 0:  # KING
                    oppositeKing = [i, j]
    # end position of king----------

    # piece position & type evaluate----------
    turnTotal, oppositeTurnTotal = 0, 0
    for i in range(len(boardPiece)):
        for j in range(len(boardPiece[0])):
            if boardGroup[i][j] == turn and boardPiece[i][j] != 7:  # Non-empty pieces
                turnTotal += positionEvaluate[boardPiece[i][j]][boardGroup[i][j]][i][j]
                turnTotal += pieceEvaluate[boardPiece[i][j]]
            elif boardGroup[i][j] == oppositeTurn and boardPiece[i][j] != 7:
                oppositeTurnTotal += positionEvaluate[boardPiece[i][j]][boardGroup[i][j]][i][j]
                oppositeTurnTotal += pieceEvaluate[boardPiece[i][j]]
    # end piece position & type evaluate----------

    # piece mobility evaluate | attack & protect evaluate----------
    moves = generateMove(boardPiece, boardGroup, turn)
    oppositeMoves = generateMove(boardPiece, boardGroup, oppositeTurn)

    for move in moves:
        if boardGroup[move[2]][move[3]] == 2:  # EMPTY
            turnTotal += mobilityEvaluate[boardPiece[move[0]][move[1]]]
        elif boardGroup[move[2]][move[3]] == oppositeTurn:  # attack
            if boardPiece[move[2]][move[3]] == 0:  # Opposite KING
                return maxValue
            else:
                turnTotal += pieceEvaluate[boardPiece[move[2]][move[3]]] * attackWeight
        elif boardGroup[move[2]][move[3]] == turn:  # protect
            turnTotal += pieceEvaluate[boardPiece[move[2]][move[3]]] * protectWeight

    for oppositeMove in oppositeMoves:
        if boardGroup[oppositeMove[2]][oppositeMove[3]] == 2:  # EMPTY
            oppositeTurnTotal += mobilityEvaluate[boardPiece[oppositeMove[0]][oppositeMove[1]]]
        elif boardGroup[oppositeMove[2]][oppositeMove[3]] == turn:  # attack
            if boardPiece[oppositeMove[2]][oppositeMove[3]] == 0:  # KING
                oppositeTurnTotal += maxValue
            else:
                oppositeTurnTotal += pieceEvaluate[boardPiece[oppositeMove[2]][oppositeMove[3]]] * attackWeight
        elif boardGroup[oppositeMove[2]][oppositeMove[3]] == oppositeTurn:  # protect
            oppositeTurnTotal += pieceEvaluate[boardPiece[oppositeMove[2]][oppositeMove[3]]] * protectWeight
    # end piece mobility evaluate | attack & protect evaluate----------

    # calculate sum
    totalScore = turnTotal - oppositeTurnTotal
    print("____end evaluate____")
    return totalScore


def scoreDFS(boardPiece, boardGroup, turn, deep):
    if deep <= 0:
        return evaluate(boardPiece, boardGroup, turn)

    moves = generateValidMove(boardPiece, boardGroup, turn)

    bestScore = -10000
    bestMove = None
    for move in moves:
        newBoardPiece = copy.deepcopy(boardPiece)
        newBoardGroup = copy.deepcopy(boardGroup)

        newBoardPiece[move[2]][move[3]] = newBoardPiece[move[0]][move[1]]
        newBoardGroup[move[2]][move[3]] = newBoardGroup[move[0]][move[1]]
        newBoardPiece[move[0]][move[1]] = 7
        newBoardGroup[move[0]][move[1]] = 2

        score = scoreDFS(newBoardPiece, newBoardGroup, 1 - turn, deep - 1)
        if score > bestScore:
            bestScore = score
            bestMove = move

    return bestScore

def bestMove(boardPiece, boardGroup, turn, deep):
    moves = generateValidMove(boardPiece, boardGroup, turn)
    bestScore = -10000
    bestMove = None
    for move in moves:
        newBoardPiece = copy.deepcopy(boardPiece)
        newBoardGroup = copy.deepcopy(boardGroup)

        newBoardPiece[move[2]][move[3]] = newBoardPiece[move[0]][move[1]]
        newBoardGroup[move[2]][move[3]] = newBoardGroup[move[0]][move[1]]
        newBoardPiece[move[0]][move[1]] = 7
        newBoardGroup[move[0]][move[1]] = 2

        score = scoreDFS(newBoardPiece, newBoardGroup, 1 - turn, deep - 1)
        if score > bestScore:
            bestScore = score
            bestMove = move
    return bestMove

boardPiece = [[3, 4, 2, 1, 0, 1, 2, 4, 3],  # 黑車馬象士將士象馬車
 [7, 7, 7, 7, 7, 7, 7, 7, 7],  # 空位
 [7, 5, 7, 7, 7, 7, 7, 5, 7],  # 黑炮
 [6, 7, 6, 7, 6, 7, 6, 7, 6],  # 黑卒
 [7, 7, 7, 7, 7, 7, 7, 7, 7],  # 空位
 [7, 7, 7, 7, 7, 7, 7, 7, 7],  # 空位
 [6, 7, 6, 7, 6, 7, 6, 7, 6],  # 紅兵
 [7, 5, 7, 7, 7, 7, 7, 5, 7],  # 紅炮
 [7, 7, 7, 7, 7, 7, 7, 7, 7],  # 空位
 [3, 4, 2, 1, 0, 1, 2, 4, 3]]  # 紅車馬象士帥士象馬車




boardGroup = [[1, 1, 1, 1, 1, 1, 1, 1, 1],  # 黑方
 [2, 2, 2, 2, 2, 2, 2, 2, 2],  # 空位
 [2, 1, 2, 2, 2, 2, 2, 1, 2],  # 黑方炮
 [1, 2, 1, 2, 1, 2, 1, 2, 1],  # 黑卒
 [2, 2, 2, 2, 2, 2, 2, 2, 2],  # 空位
 [2, 2, 2, 2, 2, 2, 2, 2, 2],  # 空位
 [0, 2, 0, 2, 0, 2, 0, 2, 0],  # 紅兵
 [2, 0, 2, 2, 2, 2, 2, 0, 2],  # 紅炮
 [2, 2, 2, 2, 2, 2, 2, 2, 2],  # 空位
 [0, 0, 0, 0, 0, 0, 0, 0, 0]]  # 紅方


turn = 0

print(bestMove(boardPiece, boardGroup, turn, 2))