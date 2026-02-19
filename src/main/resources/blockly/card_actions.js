const blocks = [
  {
    "type": "deal_damage",
    "category": "card",
    "message0": "Deal %1 damage to %2",
    "args0": [
      { "type": "input_value", "name": "DAMAGE", "check": "Number" },
      { "type": "field_dropdown", "name": "TARGET", "options": [["Opponent","OPPONENT"], ["Self","SELF"]] }
    ],
    "previousStatement": null,
    "nextStatement": null,
    "colour": 230,
    "tooltip": "Deals damage to target",
    "helpUrl": ""
  },
  {
    "type": "draw_card",
    "category": "card",
    "message0": "Draw %1 card(s)",
    "args0": [
      { "type": "input_value", "name": "COUNT", "check": "Number" }
    ],
    "previousStatement": null,
    "nextStatement": null,
    "colour": 160,
    "tooltip": "Draw cards",
    "helpUrl": ""
  }
]

blocks.forEach(block => {
  Blockly.defineBlocksWithJsonArray([
    block
  ]);

  const toolbox = document.getElementById("toolbox");
  const category = toolbox.querySelector(`category[id="${block.category}"]`);

  const blockElement = document.createElement('block');
  blockElement.setAttribute('type', block.type);
  category.appendChild(blockElement);
});