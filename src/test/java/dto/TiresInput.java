package dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TiresInput {

    private final String selectWidth;
    private final String selectProfile;
    private final String selectDiameter;
    private final String selectProtector;
    private final String enterFirstPrice;
    private final String enterSecondPrice;
}
