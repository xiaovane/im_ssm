package admin.entity.api;

public class VoteChoice {

	private Integer id;
	private String choiceId;
	private String choiceValue;
	private String voteId;
	public String getChoiceValue() {
		return choiceValue;
	}
	public void setChoiceValue(String choiceValue) {
		this.choiceValue = choiceValue;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getChoiceId() {
		return choiceId;
	}
	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}
	
	public String getVoteId() {
		return voteId;
	}
	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}
	
}
