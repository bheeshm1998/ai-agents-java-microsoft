package com.bheeshm.azure_ai_java;

import com.bheeshm.azure_ai_java.agent.StoryImageMarkdownAgent;
import com.bheeshm.azure_ai_java.utils.DalleImageGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.model.azure.AzureOpenAiChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.ArrayList;

public class MarkdownExample {

    @Value("${openai.api.key}")
    private static String openaiApiKey;

    public static void main(String[] args) {

        ChatLanguageModel chatLanguageModel = OpenAiChatModel.builder()
                .apiKey(openaiApiKey)
                .modelName("gpt-4o-mini")
                .temperature(0.1)
                .timeout(Duration.ofSeconds(120))
                .build();

        StoryImageMarkdownAgent markdownAgent = new StoryImageMarkdownAgent(chatLanguageModel);

        String storyText = "Thank You, Ma'am (by Langston Hughes)\n" +
                " She was a large woman with a  large purse that had everything in it but hammer and nails. It had a\n" +
                " long strap, and she carried it slung across her shoulder. It was about eleven o’clock at night, and she\n" +
                " was walking alone, when a boy ran up behind her and tried to snatch her purse. The strap broke\n" +
                " with the single tug the boy gave it from behind. But the boy’s weight and the weight of the purse\n" +
                " combined caused him to lose his balance so, intsead of taking off full blast as he had hoped, the\n" +
                " boy fell on his back on the sidewalk, and his legs flew up. the large woman simply turned around\n" +
                " and kicked him right square in his blue-jeaned sitter. Then she reached down, picked the boy up by\n" +
                " his shirt front, and shook him until his teeth rattled.\n" +
                " After that the woman said, “Pick up my pocketbook, boy, and give it here.” She still held him. But\n" +
                " she bent down enough to permit him to stoop and pick up her purse. Then she said, “Now ain’t\n" +
                " you ashamed of yourself?”\n" +
                " Firmly gripped by his shirt front, the boy said, “Yes’m.”\n" +
                " The woman said, “What did you want to do it for?”\n" +
                " The boy said, “I didn’t aim to.”\n" +
                " She said, “You a lie!”\n" +
                " By that time two or three people passed, stopped, turned to look, and some stood watching.\n" +
                " “If I turn you loose, will you run?” asked the woman.\n" +
                " “Yes’m,” said the boy.\n" +
                " “Then I won’t turn you loose,” said the woman. She did not release him.\n" +
                " “I’m very sorry, lady, I’m sorry,” whispered the boy.\n" +
                " “Um-hum! And your face is dirty. I got a great mind to wash your face for you. Ain’t you got\n" +
                " nobody home to tell you to wash your face?”\n" +
                " “No’m,” said the boy.\n" +
                " “Then it will get washed this evening,” said the large woman starting up the street, dragging the\n" +
                " frightened boy behind her.\n" +
                " He looked as if he were fourteen or fifteen, frail and willow-wild, in tennis shoes and blue jeans.\n" +
                " The woman said, “You ought to be my son. I would teach you right from wrong. Least I can do\n" +
                " right now is to wash your face. Are you hungry?”\n" +
                " “No’m,” said the being dragged boy. “I just want you to turn me loose.”\n" +
                " “Was I bothering you when I turned that corner?” asked the woman.\n" +
                " “No’m.”\n" +
                " 1\n" +
                " “But you put yourself in contact with me,” said the woman. “If you think that that contact is not\n" +
                " going to last awhile, you got another thought coming. When I get through with you, sir, you are\n" +
                " going to remember Mrs. Luella Bates Washington Jones.”\n" +
                " Sweat popped out on the boy’s face and he began to struggle. Mrs. Jones stopped, jerked him\n" +
                " around in front of her, put a half-nelson about his neck, and continued to drag him up the street.\n" +
                " When she got to her door, she dragged the boy inside, down a hall, and into a large kitchenette\n" +
                "furnished room at the rear of the house. She switched on the light and left the door open. The boy\n" +
                " could hear other roomers laughing and talking in the large house. Some of their doors were open,\n" +
                " too, so he knew he and the woman were not alone. The woman still had him by the neck in the\n" +
                " middle of her room.\n" +
                " She said, “What is your name?”\n" +
                " “Roger,” answered the boy.\n" +
                " “Then, Roger, you go to that sink and wash your face,” said the woman, whereupon she turned\n" +
                " him loose—at last. Roger looked at the door—looked at the woman—looked at the door—and went\n" +
                " to the sink.\n" +
                " Let the water run until it gets warm,” she said. “Here’s a clean towel.”\n" +
                " “You gonna take me to jail?” asked the boy, bending over the sink.\n" +
                " “Not with that face, I would not take you nowhere,” said the woman. “Here I am trying to get\n" +
                " home to cook me a bite to eat and you snatch my pocketbook! Maybe, you ain’t been to your\n" +
                " supper either, late as it be. Have you?”\n" +
                " “There’s nobody home at my house,” said the boy.\n" +
                " “Then we’ll eat,” said the woman, “I believe you’re hungry—or been hungry—to try to snatch my\n" +
                " pockekbook.”\n" +
                " “I wanted a pair of blue suede shoes,” said the boy.\n" +
                " “Well, you didn’t have to snatch my pocketbook to get some suede shoes,” said Mrs. Luella Bates\n" +
                " Washington Jones. “You could of asked me.”\n" +
                " “M’am?”\n" +
                " The water dripping from his face, the boy looked at her. There was a long pause. A very long\n" +
                " pause. After he had dried his face and not knowing what else to do dried it again, the boy turned\n" +
                " around, wondering what next. The door was open. He could make a dash for it down the hall. He\n" +
                " could run, run, run, run, run!\n" +
                " The woman was sitting on the day-bed. After a while she said, “I were young once and I wanted\n" +
                " things I could not get.”\n" +
                " There was another long pause. The boy’s mouth opened. Then he frowned, but not knowing he\n" +
                " frowned.\n" +
                " The woman said, “Um-hum! You thought I was going to say but, didn’t you? You thought I was\n" +
                " 2\n" +
                "going to say, but I didn’t snatch people’s pocketbooks. Well, I wasn’t going to say that.” Pause.\n" +
                " Silence. “I have done things, too, which I would not tell you, son—neither tell God, if he didn’t\n" +
                " already know. So you set down while I fix us something to eat. You might run that comb through\n" +
                " your hair so you will look presentable.”\n" +
                " In another corner of the room behind a screen was a gas plate and an icebox. Mrs. Jones got up\n" +
                " and went behind the screen. The woman did not watch the boy to see if he was going to run now,\n" +
                " nor did she watch her purse which she left behind her on the day-bed. But the boy took care to sit\n" +
                " on the far side of the room where he thought she could easily see him out of the corner of her eye,\n" +
                " if she wanted to. He did not trust the woman not to trust him. And he did not want to be mistrusted\n" +
                " now.\n" +
                " “Do you need somebody to go to the store,” asked the boy, “maybe to get some milk or\n" +
                " something?”\n" +
                " “Don’t believe I do,” said the woman, “unless you just want sweet milk yourself. I was going to\n" +
                " make cocoa out of this canned milk I got here.”\n" +
                " “That will be fine,” said the boy.\n" +
                " She heated some lima beans and ham she had in the icebox, made the cocoa, and set the table.\n" +
                " The woman did not ask the boy anything about where he lived, or his folks, or anything else that\n" +
                " would embarrass him. Instead, as they ate, she told him about her job in a hotel beauty-shop that\n" +
                " stayed open late, what the work was like, and how all kinds of women came in and out, blondes,\n" +
                " red-heads, and Spanish. Then she cut him a half of her ten-cent cake.\n" +
                " “Eat some more, son,” she said.\n" +
                " When they were finished eating she got up and said, “Now, here, take this ten dollars and buy\n" +
                " yourself some blue suede shoes. And next time, do not make the mistake of latching onto my\n" +
                " pocketbook nor nobody else’s—because shoes come by devilish like that will burn your feet. I got to\n" +
                " get my rest now. But I wish you would behave yourself, son, from here on in.”\n" +
                " She led him down the hall to the front door and opened it. “Good-night! Behave yourself, boy!”\n" +
                " she said, looking out into the street.\n" +
                " The boy wanted to say something else other than “Thank you, m’am” to Mrs. Luella Bates\n" +
                " Washington Jones, but he couldn’t do so as he turned at the barren stoop and looked back at the\n" +
                " large woman in the door. He barely managed to say “Thank you” before she shut the door. And he\n" +
                " never saw her again";

        String caption = "Lady standing with a purse";

        String markdownText = markdownAgent.process(storyText, new ArrayList<>());
        System.out.println(markdownText);
    }
}