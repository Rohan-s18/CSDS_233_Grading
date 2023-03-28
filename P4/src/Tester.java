import org.junit.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
/* 
 * Tests are structured such that if you pass more than half of cases in a test method, you will be given 1 point out of 25 for
 * that test method (0 otherwise). Therefore, some edge cases can fail while still getting full credit.
*/
public class Tester {
    private String directory = "C:\\Users\\emile\\Desktop"; // Replace with path to folder on your PC where test files exist

    // Asserts that l1 & l2 have the same elements (without regard to order), destroying l2 in the process
    private void testArrayPermutation(ArrayList<String> l1, ArrayList<String> l2) {
        for (String s : l1) {
            if (!l2.remove(s)) Assert.fail("'" + s + "' (and potentially more elements) exist too many times.");
        }
        if (!l2.isEmpty()) Assert.fail("'" + l2.get(0) + "' (and potentially more elements) are missing.");
    }
    
    @Test
    public void tokenizerFileTester() {
        Tokenizer t1 = new Tokenizer(directory + "\\test1");
        testArrayPermutation(t1.wordList(), (ArrayList<String>)Arrays.asList(new String[]{"im", "going", "to", "eat",
        "twentyfive", "pancakes"}));
    }

    @Test
    public void tokenizerFileEdgeTester() {
        Tokenizer t2 = new Tokenizer(directory + "\\test2");
        try {
            Assert.assertEquals(t2.wordList().toString(), "[]");
        } catch (NullPointerException e) {} // Allow wordList to be null for files with no normalizable words
        Tokenizer t3 = new Tokenizer(directory + "\\test3");
        Assert.assertEquals(t3.wordList().toString(), "[supercalifragilisticexpialidocious]");
        Tokenizer t4 = new Tokenizer(directory + "\\test4");
        try {
            Assert.assertEquals(t4.wordList().toString(), "[]");
        } catch (NullPointerException e) {} // Allow wordList to be null for files with no normalizable words
        Tokenizer t5 = new Tokenizer(directory + "\\test5");
        testArrayPermutation(t5.wordList(), (ArrayList<String>)Arrays.asList(new String[]{"gdynia", "10", "sopot", "sopot", "13",
        "gdańsk", "gdańsk", "36", "gdynia", "422", "białystok", "białystok", "198", "warsaw", "lublin", "173", "warsaw", "warsaw",
        "340", "gdańsk", "290", "kraków", "136", "łódź", "łódź", "126", "częstochowa", "217", "wrocław", "częstochowa", "73",
        "katowice", "258", "wrocław", "126", "łódź", "wrocław", "182", "poznań", "poznań", "63", "gniezno", "311", "warsaw",
        "katowice"}));
        Tokenizer t6 = new Tokenizer(directory + "\\test6");
        testArrayPermutation(t6.wordList(), (ArrayList<String>)Arrays.asList(new String[]{"35", "ultra", "space", "usp",
        "everything", "everywhere", "all", "at", "once", "type", "modifiers", "dark", "moves", "increase", "in", "power", "x15",
        "ability", "modifiers", "pokemon", "with", "the", "following", "abilities", "randomly", "become", "a", "different",
        "type", "at", "the", "end", "of", "each", "turn", "multitype", "rks", "system", "victory", "star", "additionally",
        "boosts", "user", "and", "allys", "attack", "and", "sp", "atk", "x15", "shadow", "shield", "additionally", "doubles",
        "the", "pokemons", "defense", "and", "sp", "def", "the", "following", "abilities", "are", "disabled", "magnet", "pull",
        "flame", "body", "plus", "minus", "blaze", "download", "forewarn", "solid", "rock", "parental", "bond", "symbiosis",
        "power", "construct", "earthen", "aura", "power", "spot", "earthinize", "cultivate", "neutralizing", "gas", "seasonal",
        "heart", "air", "lock", "additionally", "raises", "speed", "upon", "entry", "mold", "breaker", "additionally", "raises",
        "sp", "atk", "upon", "entry", "the", "statchanging", "effects", "of", "the", "following", "abilities", "are", "amplified",
        "by", "1", "stage", "beast", "boost", "supernova", "shields", "down", "additionally", "boosts", "attack", "and", "sp",
        "atk", "when", "hp", "falls", "below", "half", "spacial", "connection", "instead", "boosts", "the", "pokemons",
        "attacking", "stat", "x15", "for", "each", "psychic", "or", "fairy", "pokemon", "on", "the", "field", "serpentine",
        "intervention", "is", "activated", "an", "additional", "time", "move", "modifiers", "wind", "moves", "and", "sound",
        "moves", "fail", "upon", "use", "light", "moves", "increase", "in", "power", "x12", "the", "following", "moves",
        "increase", "in", "power", "x15", "psystrike", "aeroblast", "sacred", "fire", "mist", "ball", "origin", "pulse", 
        "precipice", "blades", "dragon", "ascent", "psycho", "boost", "roar", "of", "time", "shadow", "force", "seed", "flare",
        "judgment", "vcreate", "sacred", "sword", "secret", "sword", "fusion", "bolt", "fusion", "flare", "bolt", "strike",
        "blue", "flare", "glaciate", "ice", "burn", "freeze", "shock", "relic", "song", "oblivion", "wing", "thousand", "arrows",
        "thousand", "waves", "earth", "power", "fleur", "cannon", "prismatic", "laser", "sunsteel", "strike", "spectral",
        "thief", "moongeist", "beam", "multiattack", "continental", "crush", "core", "enforcer", "genesis", "supernova",
        "searing", "sunraze", "smash", "draco", "meteor", "ancient", "power", "menacing", "moonraze", "maelstrom", "meteor",
        "mash", "comet", "punch", "lands", "wrath", "diamond", "storm", "soulstealing", "7star", "strike", "swift", "energy",
        "ball", "photon", "geyser", "splintered", "stormshards", "light", "that", "burns", "the", "sky", "meteor", "beam",
        "meteor", "tempest", "infinite", "force", "mystical", "power", "the", "following", "moves", "increase", "in", "power",
        "x2", "vacuum", "wave", "spacial", "rend", "hyperspace", "hole", "hyperspace", "fury", "shattered", "psyche", "heart",
        "swap", "additionally", "gains", "pain", "splits", "effect", "the", "base", "accuracy", "of", "the", "following",
        "moves", "is", "increased", "to", "100", "dark", "void", "lunar", "dance", "additionally", "boosts", "all", "of", "the",
        "recipients", "stats", "trick", "room", "magic", "room", "and", "wonder", "room", "instead", "last", "for", "8", "turns",
        "the", "statchanging", "effects", "of", "the", "following", "moves", "are", "amplified", "by", "1", "stage", "cosmic",
        "power", "flash", "calm", "mind", "moonlight", "instead", "heals", "23", "max", "hp", "black", "hole", "eclipse",
        "increases", "in", "power", "x4", "nature", "power", "becomes", "spacial", "rend", "camouflage", "changes", "the",
        "users", "type", "to", "a", "random", "type", "secret", "power", "may", "lower", "the", "targets", "sp", "def",
        "terrain", "pulse", "increases", "in", "power", "x2", "and", "becomes", "a", "random", "type", "the", "following",
        "moves", "fail", "upon", "use", "earthquake", "fissure", "dig", "poison", "gas", "smokescreen", "hidden", "power",
        "instead", "becomes", "a", "random", "type", "vacuum", "wave", "becomes", "darktype", "energy", "ball", "becomes",
        "psychictype", "light", "that", "burns", "the", "sky", "decreases", "in", "power", "x05", "the", "healing", "effects",
        "of", "lunar", "blessing", "are", "doubled", "item", "modifiers", "magical", "seed", "boosts", "defense", "and", "sp",
        "def", "and", "creates", "wonder", "room", "the", "following", "items", "instead", "boost", "the", "power", "of",
        "affected", "moves", "x15", "adamant", "orb", "griseous", "orb", "lustrous", "orb", "field", "transformation", "the",
        "following", "moves", "transform", "the", "field", "into", "a", "starlight", "arena", "twinkle", "tackle", "astral",
        "barrage", "the", "following", "moves", "transform", "the", "field", "into", "a", "random", "field", "hyperspace",
        "hole", "teleport", "black", "hole", "eclipse", "transforms", "the", "field", "into", "the", "dimensional", "field",
        "created", "by", "the", "following", "moves", "searing", "sunraze", "smash", "menacing", "moonraze", "maelstrom",
        "other", "effects", "prevents", "all", "weather", "besides", "strong", "winds", "and", "generated", "field", "effects",
        "pokemon", "no", "longer", "gain", "an", "extra", "damage", "boost", "for", "using", "moves", "that", "share", "a",
        "type", "with", "them"}));
    }

    @Test
    public void tokenizerArrayTester() {
        Tokenizer t1 = new Tokenizer(new String[]{"I’m", "going", "to", "eat", "twenty-five", "pancakes."});
        testArrayPermutation(t1.wordList(), (ArrayList<String>)Arrays.asList(new String[]{"im", "going", "to", "eat",
        "twentyfive", "pancakes"}));
    }

    @Test
    public void tokenizerArrayEdgeTester() {
        Tokenizer t2 = new Tokenizer(new String[0]);
        try {
            Assert.assertEquals(t2.wordList().toString(), "[]");
        } catch (NullPointerException e) {} // Allow wordList to be null for files with no normalizable words
        Tokenizer t3 = new Tokenizer(new String[]{"supercalifragilisticexpialidocious"});
        Assert.assertEquals(t3.wordList().toString(), "[supercalifragilisticexpialidocious]");
        Tokenizer t4 = new Tokenizer(new String[]{" :)", " ;)	:(", "\n", ":/"});
        try {
            Assert.assertEquals(t4.wordList().toString(), "[]");
        } catch (NullPointerException e) {} // Allow wordList to be null for files with no normalizable words
        Tokenizer t5 = new Tokenizer(new String[]{"Gdynia 10 Sopot", "", "Sopot 13 Gdańsk\nGdańsk 36 Gdynia 422 Białystok",
        "Białystok 198 Warsaw", "Lublin 173 Warsaw", "Warsaw 340 Gdańsk 290 Kraków 136 Łódź", "Łódź 126 Częstochowa 217 Wrocław",
        "Częstochowa 73 Katowice 258 Wrocław 126 Łódź", "Wrocław 182 Poznań", "Poznań 63 Gniezno 311 Warsaw", "Katowice"});
        testArrayPermutation(t5.wordList(), (ArrayList<String>)Arrays.asList(new String[]{"gdynia", "10", "sopot", "sopot", "13",
        "gdańsk", "gdańsk", "36", "gdynia", "422", "białystok", "białystok", "198", "warsaw", "lublin", "173", "warsaw", "warsaw",
        "340", "gdańsk", "290", "kraków", "136", "łódź", "łódź", "126", "częstochowa", "217", "wrocław", "częstochowa", "73",
        "katowice", "258", "wrocław", "126", "łódź", "wrocław", "182", "poznań", "poznań", "63", "gniezno", "311", "warsaw",
        "katowice"}));
        Tokenizer t6 = new Tokenizer(new String[]{"#--------------------------- 35. Ultra Space [USP] -----------------------" +
        "---------#\n\"Everything everywhere, all at once...\"\n" +
        ":::TYPE MODIFIERS:::\n" +
        "*\tDark moves increase in power x1.5\n" +
        ":::ABILITY MODIFIERS:::\n" +
        "*\tPokemon with the following abilities randomly become a different type at the end of each turn:\n" +
        "\t\tMultitype\t\tRKS System\n" +
        "*\tVictory Star additionally boosts user and ally's Attack and Sp. Atk x1.5\n" +
        "*\tShadow Shield additionally doubles the Pokemon's Defense and Sp. Def\n" +
        "*\tThe following abilities are disabled:\n" +
        "\t\tMagnet Pull\t\tFlame Body\t\tPlus\t\t\tMinus\t\t\tBlaze\n" +
        "\t\tDownload\t\tForewarn\t\tSolid Rock\t\tParental Bond\t\tSymbiosis\n" +
        "\t\tPower Construct\t\tEarthen Aura\t\tPower Spot\t\tEarthinize\t\tCultivate\n" +
        "\t\tNeutralizing Gas\tSeasonal Heart\n" +
        "*\tAir Lock additionally raises Speed upon entry\n" +
        "*\tMold Breaker additionally raises Sp. Atk upon entry\n" +
        "*\tThe stat-changing effects of the following abilities are amplified by 1 stage:\n" +
        "\t\tBeast Boost\t\tSupernova\n" +
        "*\tShields Down additionally boosts Attack and Sp. Atk when HP falls below half\n" +
        "*\tSpacial Connection instead boosts the Pokemon's attacking stat x1.5 for each Psychic or Fairy Pokemon on the field" +
        "\n*\tSerpentine Intervention is activated an additional time\n" +
        ":::MOVE MODIFIERS:::\n" +
        "*\tWind moves and Sound moves fail upon use\n" +
        "*\tLight moves increase in power x1.2\n" +
        "*\tThe following moves increase in power x1.5:\n" +
        "\t\tPsystrike\t\tAeroblast\t\tSacred Fire\t\tMist Ball\t\tOrigin Pulse\n" +
        "\t\tPrecipice Blades\tDragon Ascent\t\tPsycho Boost\t\tRoar of Time\t\tShadow Force\n" +
        "\t\tSeed Flare\t\tJudgment\t\tV-Create\t\tSacred Sword\t\tSecret Sword\n" +
        "\t\tFusion Bolt\t\tFusion Flare\t\tBolt Strike\t\tBlue Flare\t\tGlaciate\n" +
        "\t\tIce Burn\t\tFreeze Shock\t\tRelic Song\t\tOblivion Wing\t\tThousand Arrows\n" +
        "\t\tThousand Waves\t\tEarth Power\t\tFleur Cannon\t\tPrismatic Laser\t\tSunsteel Strike\n" +
        "\t\tSpectral Thief  \tMoongeist Beam  \tMulti-Attack\t\tContinental Crush\tCore Enforcer\n" +
        "\t\tGenesis Supernova\tSearing Sunraze Smash\tDraco Meteor\t\tAncient Power\t\tMenacing Moonraze Maelstrom\n" +
        "\t\tMeteor Mash\t\tComet Punch\t\tLand's Wrath\t\tDiamond Storm\t\tSoul-Stealing 7-Star Strike\n" +
        "\t\tSwift\t\t\tEnergy Ball\t\tPhoton Geyser\t\tSplintered Stormshards\tLight That Burns the Sky\n" +
        "\t\tMeteor Beam\t\tMeteor Tempest\t\tInfinite Force\t\tMystical Power\n" +
        "*\tThe following moves increase in power x2:\n" +
        "\t\tVacuum Wave\t\tSpacial Rend\t\tHyperspace Hole\t\tHyperspace Fury\t\tShattered Psyche\n" +
        "*\tHeart Swap additionally gains Pain Split's effect\n" +
        "*\tThe base Accuracy of the following moves is increased to 100:\n" +
        "\t\tDark Void\n" +
        "* \tLunar Dance additionally boosts all of the recipient's stats\n" +
        "*\tTrick Room, Magic Room and Wonder Room instead last for 8 turns\n" +
        "*\tThe stat-changing effects of the following moves are amplified by 1 stage:\n" +
        "\t\tCosmic Power\t\tFlash\t\t\tCalm Mind\n" +
        "*\tMoonlight instead heals 2/3 max HP\n" +
        "*   \tBlack Hole Eclipse increases in power x4\n" +
        "* \tNature Power becomes Spacial Rend\n" +
        "*\tCamouflage changes the user's type to a random type\n" +
        "*\tSecret Power may lower the target's Sp. Def\n" +
        "*\tTerrain Pulse increases in power x2 and becomes a random type\n" +
        "*\tThe following moves fail upon use:\n" +
        "\t\tEarthquake\t\tFissure\t\t\tDig\t\t\tPoison Gas\t\tSmokescreen\n" +
        "*\tHidden Power instead becomes a random type\n" +
        "*\tVacuum Wave becomes Dark-type\n" +
        "*\tEnergy Ball becomes Psychic-type\n" +
        "*\tLight That Burns the Sky decreases in power x0.5\n" +
        "*\tThe healing effects of Lunar Blessing are doubled\n" +
        ":::ITEM MODIFIERS:::\n" +
        "*\tMagical Seed boosts Defense and Sp. Def, and creates Wonder Room\n" +
        "*\tThe following items instead boost the power of affected moves x1.5:\n" +
        "\t\tAdamant Orb\t\tGriseous Orb\t\tLustrous Orb\n" +
        ":::FIELD TRANSFORMATION:::\n" +
        "*\tThe following moves transform the field into a Starlight Arena:\n" +
        "\t\tTwinkle Tackle\t\tAstral Barrage\n" +
        "*\tThe following moves transform the field into a random field:\n" +
        "\t\tHyperspace Hole\t\tTeleport\n" +
        "*\tBlack Hole Eclipse transforms the field into the Dimensional Field\n" +
        "*\tCreated by the following moves:\n" +
        "\t\tSearing Sunraze Smash\tMenacing Moonraze Maelstrom\n" +
        ":::OTHER EFFECTS:::\n" +
        "*\tPrevents all weather (besides Strong Winds) and generated Field Effects\n" +
        "*\tPokemon no longer gain an extra damage boost for using moves that share a type with them"});
        testArrayPermutation(t6.wordList(), (ArrayList<String>)Arrays.asList(new String[]{"35", "ultra", "space", "usp",
        "everything", "everywhere", "all", "at", "once", "type", "modifiers", "dark", "moves", "increase", "in", "power", "x15",
        "ability", "modifiers", "pokemon", "with", "the", "following", "abilities", "randomly", "become", "a", "different",
        "type", "at", "the", "end", "of", "each", "turn", "multitype", "rks", "system", "victory", "star", "additionally",
        "boosts", "user", "and", "allys", "attack", "and", "sp", "atk", "x15", "shadow", "shield", "additionally", "doubles",
        "the", "pokemons", "defense", "and", "sp", "def", "the", "following", "abilities", "are", "disabled", "magnet", "pull",
        "flame", "body", "plus", "minus", "blaze", "download", "forewarn", "solid", "rock", "parental", "bond", "symbiosis",
        "power", "construct", "earthen", "aura", "power", "spot", "earthinize", "cultivate", "neutralizing", "gas", "seasonal",
        "heart", "air", "lock", "additionally", "raises", "speed", "upon", "entry", "mold", "breaker", "additionally", "raises",
        "sp", "atk", "upon", "entry", "the", "statchanging", "effects", "of", "the", "following", "abilities", "are", "amplified",
        "by", "1", "stage", "beast", "boost", "supernova", "shields", "down", "additionally", "boosts", "attack", "and", "sp",
        "atk", "when", "hp", "falls", "below", "half", "spacial", "connection", "instead", "boosts", "the", "pokemons",
        "attacking", "stat", "x15", "for", "each", "psychic", "or", "fairy", "pokemon", "on", "the", "field", "serpentine",
        "intervention", "is", "activated", "an", "additional", "time", "move", "modifiers", "wind", "moves", "and", "sound",
        "moves", "fail", "upon", "use", "light", "moves", "increase", "in", "power", "x12", "the", "following", "moves",
        "increase", "in", "power", "x15", "psystrike", "aeroblast", "sacred", "fire", "mist", "ball", "origin", "pulse", 
        "precipice", "blades", "dragon", "ascent", "psycho", "boost", "roar", "of", "time", "shadow", "force", "seed", "flare",
        "judgment", "vcreate", "sacred", "sword", "secret", "sword", "fusion", "bolt", "fusion", "flare", "bolt", "strike",
        "blue", "flare", "glaciate", "ice", "burn", "freeze", "shock", "relic", "song", "oblivion", "wing", "thousand", "arrows",
        "thousand", "waves", "earth", "power", "fleur", "cannon", "prismatic", "laser", "sunsteel", "strike", "spectral",
        "thief", "moongeist", "beam", "multiattack", "continental", "crush", "core", "enforcer", "genesis", "supernova",
        "searing", "sunraze", "smash", "draco", "meteor", "ancient", "power", "menacing", "moonraze", "maelstrom", "meteor",
        "mash", "comet", "punch", "lands", "wrath", "diamond", "storm", "soulstealing", "7star", "strike", "swift", "energy",
        "ball", "photon", "geyser", "splintered", "stormshards", "light", "that", "burns", "the", "sky", "meteor", "beam",
        "meteor", "tempest", "infinite", "force", "mystical", "power", "the", "following", "moves", "increase", "in", "power",
        "x2", "vacuum", "wave", "spacial", "rend", "hyperspace", "hole", "hyperspace", "fury", "shattered", "psyche", "heart",
        "swap", "additionally", "gains", "pain", "splits", "effect", "the", "base", "accuracy", "of", "the", "following",
        "moves", "is", "increased", "to", "100", "dark", "void", "lunar", "dance", "additionally", "boosts", "all", "of", "the",
        "recipients", "stats", "trick", "room", "magic", "room", "and", "wonder", "room", "instead", "last", "for", "8", "turns",
        "the", "statchanging", "effects", "of", "the", "following", "moves", "are", "amplified", "by", "1", "stage", "cosmic",
        "power", "flash", "calm", "mind", "moonlight", "instead", "heals", "23", "max", "hp", "black", "hole", "eclipse",
        "increases", "in", "power", "x4", "nature", "power", "becomes", "spacial", "rend", "camouflage", "changes", "the",
        "users", "type", "to", "a", "random", "type", "secret", "power", "may", "lower", "the", "targets", "sp", "def",
        "terrain", "pulse", "increases", "in", "power", "x2", "and", "becomes", "a", "random", "type", "the", "following",
        "moves", "fail", "upon", "use", "earthquake", "fissure", "dig", "poison", "gas", "smokescreen", "hidden", "power",
        "instead", "becomes", "a", "random", "type", "vacuum", "wave", "becomes", "darktype", "energy", "ball", "becomes",
        "psychictype", "light", "that", "burns", "the", "sky", "decreases", "in", "power", "x05", "the", "healing", "effects",
        "of", "lunar", "blessing", "are", "doubled", "item", "modifiers", "magical", "seed", "boosts", "defense", "and", "sp",
        "def", "and", "creates", "wonder", "room", "the", "following", "items", "instead", "boost", "the", "power", "of",
        "affected", "moves", "x15", "adamant", "orb", "griseous", "orb", "lustrous", "orb", "field", "transformation", "the",
        "following", "moves", "transform", "the", "field", "into", "a", "starlight", "arena", "twinkle", "tackle", "astral",
        "barrage", "the", "following", "moves", "transform", "the", "field", "into", "a", "random", "field", "hyperspace",
        "hole", "teleport", "black", "hole", "eclipse", "transforms", "the", "field", "into", "the", "dimensional", "field",
        "created", "by", "the", "following", "moves", "searing", "sunraze", "smash", "menacing", "moonraze", "maelstrom",
        "other", "effects", "prevents", "all", "weather", "besides", "strong", "winds", "and", "generated", "field", "effects",
        "pokemon", "no", "longer", "gain", "an", "extra", "damage", "boost", "for", "using", "moves", "that", "share", "a",
        "type", "with", "them"}));
    }

    @Test
    public void hashTableEmptyTester() {
        HashTable<Integer> t1 = new HashTable<>();
        Assert.assertEquals(0, t1.size());
        HashTable<String> t2 = new HashTable<>();
        Assert.assertEquals(0, t2.size());
    }

    @Test
    public void hashTableCapacityTester() {
        HashTable<Integer> t1 = new HashTable<>(2);
        Assert.assertEquals(0, t1.size());
        HashTable<String> t2 = new HashTable<>(36);
        Assert.assertEquals(0, t2.size());
        HashTable<Math> t3 = new HashTable<>(0);
        Assert.assertEquals(0, t3.size());
        try {
            new HashTable<Character>(-1);
            Assert.fail("Allows negative table size.");
        } catch (IllegalArgumentException e) {} // Should throw exception
    }

    @Test
    public void putTester() {
        HashTable<Integer> t1 = new HashTable<>(53);
        t1.put("55", 55);
        t1.put("43", 43);
        t1.put("9", 9);
        Assert.assertEquals(55, (int)t1.get("55"));
        Assert.assertEquals(43, (int)t1.get("43"));
        Assert.assertEquals(9, (int)t1.get("9"));
        Assert.assertEquals(3, t1.size());
    }

    @Test
    public void putEdgeTester() {
        HashTable<Integer> t1 = new HashTable<>(0);
        t1.put("55", 55);
        Assert.assertEquals(55, (int)t1.get("55"));
        Assert.assertEquals(1, t1.size());
        HashTable<Integer> t2 = new HashTable<>(8);
        for (int i = 0; i < 5; i++) {
            t2.put((char)('a'+i*7) + "", i); // Should cause infinite loop with quadratic probing after 3 insertions
        }
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(i, (int)t2.get((char)('a'+i*7) + ""));
        }
        Assert.assertEquals(5, t2.size());
        HashTable<Integer> t3 = new HashTable<>(2);
        t3.put("first key", 1);
        t3.put("second key", 2);
        t3.put("third key", 3); // Rehash should have been done by now
        Assert.assertEquals(1, (int)t3.get("first key"));
        Assert.assertEquals(2, (int)t3.get("second key"));
        Assert.assertEquals(3, (int)t3.get("third key"));
        Assert.assertEquals(3, t3.size());
        HashTable<Integer> t4 = new HashTable<>(7);
        t4.put("first key", 1);
        t4.put("first key", 11);
        int val = (int)t4.get("first key");
        Assert.assertTrue(val == 11 || val == 12);
        Assert.assertEquals(1, t4.size());
        HashTable<String> t5 = new HashTable<>(7);
        t5.put("first key", "initial");
        t5.put("first key", "new");
        Assert.assertEquals("new", t5.get("first key"));
        Assert.assertEquals(1, t5.size());
    }

    @Test
    public void getTester() {
        HashTable<Integer> t1 = new HashTable<>(7);
        t1.put("a", 1);
        t1.put("b", 2);
        t1.put("c", 3);
        Assert.assertEquals(2, (int)t1.get("b"));
    }

    @Test
    public void getEdgeTester() {
        HashTable<Integer> t1 = new HashTable<>(0);
        try {
            t1.get("k");
            Assert.fail("Allows getting of nonexistent values.");
        } catch (NoSuchElementException e) {} // Should throw exception
        HashTable<String> t2 = new HashTable<>(7);
        t2.put("a", "aString");
        Assert.assertEquals("aString", t2.get("a"));
        t2.put("b", "bString");
        t2.put("h", "hString"); // Collides with a in table if using hashCode function
        Assert.assertEquals("aString", t2.get("a"));
        Assert.assertEquals("hString", t2.get("h"));
    }

    @Test
    public void removeTester() {
        HashTable<Integer> t1 = new HashTable<>(7);
        t1.put("a", 1);
        t1.put("b", 2);
        t1.put("c", 3);
        Assert.assertEquals(2, (int)t1.remove("b"));
        try {
            t1.get("b");
            Assert.fail("Failed to remove the value.");
        } catch (NoSuchElementException e) {}
    }

    @Test
    public void removeEdgeTester() {
        HashTable<Integer> t1 = new HashTable<>(0);
        try {
            t1.remove("k");
            Assert.fail("Allows removal from empty table.");
        } catch (NoSuchElementException e) {} // Should throw exception
        HashTable<String> t2 = new HashTable<>(7);
        t2.put("a", "aString");
        Assert.assertEquals("aString", t2.remove("a"));
        try {
            t2.get("a");
            Assert.fail("Failed to remove the value.");
        } catch (NoSuchElementException e) {}
        HashTable<String> t3 = new HashTable<>(11);
        t3.put("a", "aString");
        t3.put("b", "bString");
        t3.put("l", "lString"); // Collides with a in table if using hashCode function
        Assert.assertEquals("aString", t3.remove("a"));
        try {
            t3.get("a");
            Assert.fail("Failed to remove the value.");
        } catch (NoSuchElementException e) {}
        try {
            t3.remove("h");
            Assert.fail("Allows removal of nonexistent elements.");
        } catch (NoSuchElementException e) {}
        t3.put("a", "new aString");
        Assert.assertEquals("lString", t3.remove("l"));
        try {
            t3.get("l");
            Assert.fail("Failed to remove the value.");
        } catch (NoSuchElementException e) {}
        Assert.assertEquals("new aString", t3.remove("a"));
        try {
            t3.get("a");
            Assert.fail("Failed to remove the value.");
        } catch (NoSuchElementException e) {}
    }

    @Test
    public void sizeTester() {}

    @Test
    public void wordStatFileTester() {}

    @Test
    public void wordStatArrayTester() {}

    @Test
    public void wordCountTester() {}

    @Test
    public void wordCountEdgeTester() {}

    @Test
    public void wordRankTester() {}

    @Test
    public void wordRankEdgeTester() {}

    @Test
    public void mostCommonWordsTester() {}

    @Test
    public void mostCommonWordsEdgeTester() {}

    @Test
    public void leastCommonWordsTester() {}

    @Test
    public void leastCommonWordsEdgeTester() {}

    @Test
    public void mostCommonCollocationsTester() {}

    @Test
    public void mostCommonCollocationsEdgeTester() {}
}
