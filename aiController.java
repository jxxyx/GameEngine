import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class aiController {
    private String npcState; 
    private float npcHealth; 
    private List<Skill> availableSkills;
    private Skill previousSkill;
    private Skill buffSkill;
    private Random randomGenerator;
    private int skillPoints;

    public AIController(npcCharacter npcCharacter, playerCharacter playerCharacter) {
        // npcStatus = StatsManager.getNPCStatus(); 
        this.npcHealth = StatManager.getNPCHealth();
        this.npcCharacter = npcCharacter;
        this.playerCharacter = playerCharacter;
        this.availableSkills = new ArrayList<>();
        this.previousSkill = null; // Initialized to null since no skill has been used yet
        this.buffSkill = null; // Placeholder, assign the actual buff skill here
        this.randomGenerator = new Random();
    }

    public boolean getTurn() {
        // Check if the NPC Pokemon is asleep or can perform an action
        return !npcCharacter.statManager.getNPCStatus().equals("sleep");
    }

    public void updateAvailableSkills() {
        // Assuming 'skillpoints' is a List of Skills and 'Skill' is a class representing a skill
        for (Skill skill : npcCharacter.getSkillpoints()) {
            if (npcCharacter.getSkillpoints().size() > 0) {
                // Ensure that buff skills are not used two times in a row
                if (!skill.equals(buffSkill) || (previousSkill == null || !previousSkill.equals(buffSkill))) {
                    availableSkills.add(skill);
                }
            }
        }
    }

    public void chooseAttack() {    
        // Randomly choose an attack out of available skills
        if (!availableSkills.isEmpty()) {
            int index = randomGenerator.nextInt(availableSkills.size());
            Skill chosenSkill = availableSkills.get(index);
            
            // Perform the chosen skill
            performSkill(chosenSkill);
            
            // Update the previousSkill with the skill that was just used
            previousSkill = chosenSkill;
        }
    }

    private void performSkill(Skill skill) {
        // Implement the logic to use the skill during the battle
        // This may include applying the skill effects to the opponent and reducing skill points, etc.
    }

    public void takeTurn() {
        if (getTurn()) {
            updateAvailableSkills();
            chooseAttack();
        }
    }
}