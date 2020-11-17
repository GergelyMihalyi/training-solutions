package references.socialnetwork;

import java.util.ArrayList;
import java.util.List;

public class SocialNetwork {
    private List<Member> members = new ArrayList<>();

    public void addMember(String name) {
        members.add(new Member(name));
    }

    public void connect(String name, String otherName) {
        Member member1 = findByName(name);
        Member member2 = findByName(otherName);

        member1.connectMember(member2);
    }

    public List<String> bidirectionalConnections() {
        List<String> connectedMembers = new ArrayList<>();
        for (Member member : members) {
            for(Member connectMember: member.getConnection()){
                if(connectMember.getConnection().contains(member)){
                    connectedMembers.add(member.getName() + " - " + connectMember.getName());
                }
            }

        }
        return connectedMembers;
    }

    private Member findByName(String name) {
        for (Member member : members) {
            if (member.getName().equals(name)) {
                return member;
            }

        }
        return null;
    }
}
