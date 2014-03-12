/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.rkpx2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


/**
 * Class representing a Gpio.
 *
 * see {@link #gpio_request()},{@link #gpio_free()} for details.
 */
public class Gpio {
	private static final String TAG = "GPIO";
	
	private int mPort;
	private boolean isGpioPortPrepared = false;
		
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PA0 =  160 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PA1 =  161 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PA2 =  162 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PA3 =  163 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PA4 =  164 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PA5 =  165 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PA6 =  166 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PA7 =  167 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PB0 =  168 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PB1 =  169 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PB2 =  170 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PB3 =  171 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PB4 =  172 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PB5 =  173 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PB6 =  174 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PB7 =  175 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PC0 =  176 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PC1 =  177 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PC2 =  178 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PC3 =  179 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PC4 =  180 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PC5 =  181 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PC6 =  182 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PC7 =  183 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PD0 =  184 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PD1 =  185 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PD2 =  186 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PD3 =  187 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PD4 =  188 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PD5 =  189 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PD6 =  190 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN0_PD7 =  191 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PA0 =  192 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PA1 =  193 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PA2 =  194 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PA3 =  195 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PA4 =  196 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PA5 =  197 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PA6 =  198 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PA7 =  199 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PB0 =  200 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PB1 =  201 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PB2 =  202 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PB3 =  203 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PB4 =  204 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PB5 =  205 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PB6 =  206 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PB7 =  207 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PC0 =  208 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PC1 =  209 ;
	/**
	 * A *constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PC2 =  210 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PC3 =  211 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PC4 =  212 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PC5 =  213 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PC6 =  214 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PC7 =  215 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PD0 =  216 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PD1 =  217 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PD2 =  218 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PD3 =  219 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PD4 =  220 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PD5 =  221 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PD6 =  222 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN1_PD7 =  223 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PA0 =  224 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PA1 =  225 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PA2 =  226 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PA3 =  227 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PA4 =  228 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PA5 =  229 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PA6 =  230 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PA7 =  231 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PB0 =  232 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PB1 =  233 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PB2 =  234 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PB3 =  235 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PB4 =  236 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PB5 =  237 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PB6 =  238 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PB7 =  239 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PC0 =  240 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PC1 =  241 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PC2 =  242 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PC3 =  243 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PC4 =  244 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PC5 =  245 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PC6 =  246 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PC7 =  247 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PD0 =  248 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PD1 =  249 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PD2 =  250 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PD3 =  251 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PD4 =  252 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PD5 =  253 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PD6 =  254 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN2_PD7 =  255 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PA0 =  256 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PA1 =  257 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PA2 =  258 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PA3 =  259 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PA4 =  260 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PA5 =  261 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PA6 =  262 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PA7 =  263 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PB0 =  264 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PB1 =  265 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PB2 =  266 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PB3 =  267 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PB4 =  268 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PB5 =  269 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PB6 =  270 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PB7 =  271 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PC0 =  272 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PC1 =  273 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PC2 =  274 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PC3 =  275 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PC4 =  276 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PC5 =  277 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PC6 =  278 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PC7 =  279 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PD0 =  280 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PD1 =  281 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PD2 =  282 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PD3 =  283 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PD4 =  284 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PD5 =  285 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PD6 =  286 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN3_PD7 =  287 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PA0 =  288 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PA1 =  289 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PA2 =  290 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PA3 =  291 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PA4 =  292 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PA5 =  293 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PA6 =  294 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PA7 =  295 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PB0 =  296 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PB1 =  297 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PB2 =  298 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PB3 =  299 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PB4 =  300 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PB5 =  301 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PB6 =  302 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PB7 =  303 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PC0 =  304 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PC1 =  305 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PC2 =  306 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PC3 =  307 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PC4 =  308 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PC5 =  309 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PC6 =  310 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PC7 =  311 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PD0 =  312 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PD1 =  313 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PD2 =  314 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PD3 =  315 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PD4 =  316 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PD5 =  317 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PD6 =  318 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN4_PD7 =  319 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN6_PA0 =  352 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN6_PA1 =  353 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN6_PA2 =  354 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN6_PA3 =  355 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN6_PA4 =  356 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN6_PA5 =  357 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN6_PA6 =  358 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN6_PA7 =  359 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN6_PB0 =  360 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN6_PB1 =  361 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN6_PB2 =  362 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN6_PB3 =  363 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN6_PB4 =  364 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN6_PB5 =  365 ;
	/**
	 * A constant representing a gpio port of rkpx2:  {@value}.
	 */
	public static final int RKPX2_PIN6_PB6 =  366 ;
	/**
	 * A constant representing a gpio port of rkpx2,  {@value}.
	 */
	public static final int RKPX2_PIN6_PB7 =  367 ;

    /**
     * A constant describing a gpio direction type, {@value}.
     */
	public static final int TYPE_DIRECTION_IN = -1;
    /**
     * A constant describing a gpio direction type, {@value}.
     */
	public static final int TYPE_DIRECTION_OUT = -2;
    /**
     * A constant describing a gpio direction type, {@value}.
     */
	public static final int TYPE_VALUE_HIGH = 1;
    /**
     * A constant describing a gpio direction type, {@value}.
     */
	public static final int TYPE_VALUE_LOW = 0;
    /**
     * A constant describing a gpio direction type, {@value}. This TYPE_UNKONW is illegal
     */
	public static final int TYPE_UNKONW = -10000;
	
	//File of Gpio node
	private File mGpioExport = null;
	private File mGpioUnExport = null;
	private File mGpioPort = null;
	private File mGpioPortDirection = null;
	private File mGpioPortValue = null;
	
	//node string
	private String gpio_export = "/sys/class/gpio/export";	
	private String gpio_unexport = "/sys/class/gpio/unexport";	
	private String gpio_port = "/sys/class/gpio/gpio";	
	
	/**
     * Constructor function of Gpio, you should specify a number corresponding with the port.
     * see {@link #gpio_request()},{@link #gpio_free()} for more details.
     * @param port {@link #RKPX2_PIN0_PA0},{@link #RKPX2_PIN0_PA1},...,etc. You should only use the defined ports, but notice some of them have been used alreay.
     * see {@link #gpio_request()} for more information.  
     */
	public Gpio(int port) {
		mPort = port;
		mGpioExport = new File(gpio_export);
		mGpioUnExport = new File(gpio_unexport);
		isGpioPortPrepared = prepare_gpio_port(mPort);
		System.out.println("GPIO contstuct, isPrepared: " + isGpioPortPrepared);
	}

    /**
     * when you decide to use a gpio {@link #Gpio(int)}, you should first call this function, and check its return value
     * if return true, means you can use this gpio {@link #Gpio(int)}; if return false, means this gpio {@link #Gpio(int)} has already be used.
     *      */
	public boolean gpio_request() {
		return isGpioPortPrepared;
	}
	
    /**
     * 
     * free the port you have already hold
     * 
     * @see #gpio_request
     * 
     */
	public void gpio_free() {
		writeGpioNode(mGpioUnExport, mPort);
	}
	
	/**
     * set the gpio direction as input or output
     * @param flag {@link #TYPE_DIRECTION_OUT} , {@link #TYPE_DIRECTION_IN}
     * @param value {@link #TYPE_VALUE_HIGH} , {@link #TYPE_VALUE_LOW}
     * @see #getDirectionValue
     * @see #setPortValue(int)
     */
	public void setDirectionValue(int flag, int value) {
		writeGpioNode(mGpioPortDirection, flag);
		writeGpioNode(mGpioPortValue, value);
	}
	
    /**
     * return the flag {@link #TYPE_DIRECTION_OUT}, {@link #TYPE_DIRECTION_IN}, {@link #TYPE_UNKONW} of the gpio direction
     * @return Returns the direction value of gpio port
     * @see #setDirectionValue
     * @see #setPortValue(int)
     * @see #getPortValue()
     * 
     */
	public int getDirectionValue(){
		String string = null;
		if (mGpioPortDirection.exists()){
			string = readGpioNode(mGpioPortDirection);
		}
		int value = 0;
		if (string.equals("in")){
			value = TYPE_DIRECTION_IN;
		} else if (string.equals("out")){
			value = TYPE_DIRECTION_OUT;
		} else {
			value = TYPE_UNKONW;
		}
		return value;
	}
	
	/**
     * return the value {@link #TYPE_VALUE_HIGH},{@link #TYPE_VALUE_LOW} of gpio port
     * @return Returns the gpio port value
     * @see #getPortValue()
     * @see #getDirectionValue()
     */
	public int getPortValue() {
		String string = readGpioNode(mGpioPortValue);
		int value = 0;
		if (string.equals("0")){
			value = TYPE_VALUE_LOW;
		} else if (string.equals("1")){
			value = TYPE_VALUE_HIGH;
		}
		return value;
	}
	
	/**
     * set a flag {@link #TYPE_VALUE_HIGH},{@link #TYPE_VALUE_LOW} to gpio port
     * @param flag {@link #TYPE_VALUE_HIGH},{@link #TYPE_VALUE_LOW}
     * @see #getPortValue
     * @see #getDirectionValue()
     */	
	public void setPortValue(int flag) {
		writeGpioNode(mGpioPortValue, flag);
	}
	
	/**
     * 
     * @return Returns the result of gpio request
     * @see #gpio_request
     * 
     */	
	private boolean prepare_gpio_port(int port) {
		if (mGpioExport.exists()) {
			writeGpioNode(mGpioExport, port);
			String path = gpio_port + port;
            String path_direction = path + "/direction";
            String path_value = path + "/value";
            
            System.out.println("prepare gpio port: " + port);
            System.out.println(path);
            System.out.println(path_direction);
            System.out.println(path_value);
            
            mGpioPort = new File(path);
            mGpioPortDirection = new File(path_direction);
            mGpioPortValue = new File(path_value);
		}
		return  mGpioPort.exists() && mGpioPortDirection.exists() && mGpioPortValue.exists();
	}

	private void writeGpioNode(File file, int flag) {
		if (file.exists()) {
			System.out.println("write " + flag + " to " + file);
		    try {
		          FileOutputStream fos = new FileOutputStream(file);
		          OutputStreamWriter outputWrite = new OutputStreamWriter(fos);
		          PrintWriter  print = new PrintWriter(outputWrite);
		          if (flag > 159){ //this means flag representing a gpio port number
		        	  print.print(flag);
		          }else { //this meas flag representing a type for a gpio direction
		        	  switch (flag) {
		        	  case TYPE_DIRECTION_IN:
		        		  print.print("in");
		        		  break;
		        	  case TYPE_DIRECTION_OUT:
		        		  print.print("out");
		        		  break;
		        	  case TYPE_VALUE_HIGH:
		        		  print.print(1);
		        		  break;
		        	  case TYPE_VALUE_LOW:
		        		  print.print(0);
		        		  break;
		        	  default:
		        		  break;
		          }
		          }
		       print.flush();
               fos.close();
		    } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}
	
	@SuppressWarnings({ "unused", "resource" })
	private String readGpioNode(File file){
		BufferedReader reader = null;
		String string = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			
			string = reader.readLine();
				
			reader.close();
		} catch(IOException  e){
			e.printStackTrace();
		}finally{
			
		}
		return string;		
	}
}