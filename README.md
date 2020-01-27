# EP_IAA
OBJETIVO

O objetivo deste exercício é ajudar o explorador a encontrar um caminho em um labirinto, dada
uma posição de partida e outra de destino.
Havendo mais de um caminho possível, deve ser selecionado
aquele considerado melhor a partir de um certo critério escolhido para a execução do programa. O labirinto
é representado por uma matriz e a movimentação pelo labirinto pode se dar em 4 direções: para cima, para
baixo, para a esquerda e para a direita. Um caminho que leva o explorador da origem ao destino só pode
passar por cada posição uma única vez. Além disso, podem haver itens, cada um com um determinado valor e
peso, espalhados pelo labirinto. Sempre que o explorador encontra um item, ele é obrigado a coletar o item e
lavá-lo consigo. Embora coletar itens seja vantajoso pelo valor em si de cada item, os itens também possuem
peso, o que aumenta o tempo que o explorador leva para percorrer um determinado caminho. Quatro são
os critérios que devem ser considerados para determinar qual o melhor caminho que leva o explorador da
origem ao destino:
1. Caminho mais curto. Isto é, que passe pelo menor número possível de casas .
2. Caminho mais longo. Isto é, que passe pelo maior número possível de casas (ok, este é um
critério estranho, mas pense que o explorador possui motivos para visitar o maior número de casas
possível sem passar mais de uma vez por uma mesma casa).
3. Caminho mais valioso. Isto é, que maximiza o valor dos itens coletados .
4. Caminho mais rápido.
Ou seja, aquele minimiza o tempo que se leva para chegar no
destino (note que não necessariamente equivale ao mais curto, pois depende dos itens que são coletados
no caminho, o que por sua vez afeta o tempo necessário para percorrê-lo).
