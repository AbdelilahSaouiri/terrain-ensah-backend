package net.ensah.ensahterrain.dto;

import java.io.Serializable;
import java.util.Date;

public record ErrorMessageResponse(Date timesTamp, String message) implements Serializable {
}
